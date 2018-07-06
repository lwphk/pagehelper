package com.github.lwphk.pagehelper.dialect;

import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lwphk.pagehelper.plugins.PaginationStatementHandlerInterceptor;

/**
 * 
 * @author lwp
 *
 */
public class DialectFactory {

	private static final Logger logger = LoggerFactory.getLogger(PaginationStatementHandlerInterceptor.class);

	public static Class<?> clazz = null;

	public static Dialect buildDialect(Configuration configuration) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (clazz == null) {
			synchronized (DialectFactory.class) {
				if (clazz == null) {
					String dialectClass = configuration.getVariables().getProperty("dialectClass");
					if(dialectClass != null && !"".equals(dialectClass)) {
						clazz = Class.forName(dialectClass);
					}else {
						logger.error("dialectClass未配置");
						throw new RuntimeException("请检查 mybatis-config.xml 中  dialectClass 是否配置正确");
					}
				}
			}
		}
		return (Dialect) clazz.newInstance();
	}
}
