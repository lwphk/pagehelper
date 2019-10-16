package com.github.lwphk.pagehelper.plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lwphk.pagehelper.common.Page;
import com.github.lwphk.pagehelper.dialect.Dialect;
import com.github.lwphk.pagehelper.dialect.DialectFactory;

/**
 * @author lwp
 **/
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PaginationStatementHandlerInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(PaginationStatementHandlerInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		ParameterHandler parameterHandler = statementHandler.getParameterHandler();
		BoundSql boundSql = statementHandler.getBoundSql();

		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		// 没有分页参数
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}

		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		Dialect dialect = DialectFactory.buildDialect(configuration);
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		
		if (logger.isDebugEnabled()) {
			logger.debug("原SQL: " + originalSql);
		}
		
		// 获取总记录数
		Page<?> page = (Page<?>) rowBounds;
		String countSql = dialect.getCountString(originalSql);
		Connection connection = (Connection) invocation.getArgs()[0];
		int total = getTotal(parameterHandler, connection, countSql);
		page.setTotalCount(total);

		// 设置物理分页语句
		metaStatementHandler.setValue("delegate.boundSql.sql",
				dialect.getLimitString(originalSql, page.getOffset(), page.getLimit()));
		// 屏蔽mybatis原有分页
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		if (logger.isDebugEnabled()) {
			logger.debug("分页SQL : " + boundSql.getSql());
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);

	}

	@Override
	public void setProperties(Properties properties) {
	}

	/**
	 * 获取总计录
	 * 
	 * @param parameterHandler
	 * @param connection
	 * @param countSql
	 * @return
	 * @throws Exception
	 */
	private int getTotal(ParameterHandler parameterHandler, Connection connection, String countSql) throws Exception {
		// MetaObject metaStatementHandler =
		// MetaObject.forObject(parameterHandler);
		// Object parameterObject =
		// metaStatementHandler.getValue("parameterObject");
		// TODO 缓存具有相同SQL语句和参数的总数
		int count = 0;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		try {
			prepareStatement = connection.prepareStatement(countSql);
			parameterHandler.setParameters(prepareStatement);
			rs = prepareStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取总记录数出错");
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {}
				
			}
			if(prepareStatement != null) {
				try {
					prepareStatement.close();
				} catch (Exception e2) {}
				
			}
		}
		
		return count;
	}
}
