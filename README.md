# pagehelper

mybatis 分页插件

	git clone https://github.com/lwphk/pagehelper.git

	cd pagehelper

	mvn clean install

1.--------------------------pom.xml----------------------------

	<dependency>
			<groupId>com.github.lwphk.pagehelper</groupId>
			<artifactId>pagehelper</artifactId>
			<version>1.0</version>
	</dependency>


2.-------------------------mybatis-config.xml------------------

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	
	<properties>
    <!--数据库方言-->
		<property name="dialectClass" value="com.github.lwphk.pagehelper.dialect.MySql5Dialect"/>
	</properties>
	
	<!-- 配置mybatis的缓存，延迟加载等等一系列属性 -->
	<settings>

		<!-- 全局映射器启用缓存 -->
		<setting name="cacheEnabled" value="true" />

		<!-- 查询时，关闭关联对象即时加载以提高性能 -->
		<setting name="lazyLoadingEnabled" value="true" />

		<!-- 对于未知的SQL查询，允许返回不同的结果集以达到通用的效果 -->
		<setting name="multipleResultSetsEnabled" value="true" />
		<setting name="useColumnLabel" value="true" />
		<setting name="useGeneratedKeys" value="true" />
		<!-- 给予被嵌套的resultMap以字段-属性的映射支持 FULL,PARTIAL -->
		<setting name="autoMappingBehavior" value="PARTIAL" />
		<!-- 对于批量更新操作缓存SQL以提高性能 BATCH,SIMPLE -->
		<!-- <setting name="defaultExecutorType" value="BATCH" /> -->
		<!-- 数据库超过25000秒仍未响应则超时 -->
		<!-- <setting name="defaultStatementTimeout" value="25000" /> -->
		<!-- Allows using RowBounds on nested statements -->
		<setting name="safeRowBoundsEnabled" value="false" />
		<setting name="mapUnderscoreToCamelCase" value="true" />
		<setting name="localCacheScope" value="SESSION" />
		<setting name="jdbcTypeForNull" value="OTHER" />
		<!-- Specifies which Object's methods trigger a lazy load -->
		<setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString" />
		<!-- 设置关联对象加载的形态，此处为按需加载字段(加载字段由SQL指 定)，不会加载关联表的所有字段，以提高性能 -->
		<setting name="aggressiveLazyLoading" value="false" />
	</settings>

	<typeAliases>
		<package name="com.github.lwp.demo.dao.entity" />
	</typeAliases>
 
<plugins>
     <!--分页插件-->
		<plugin
			interceptor="com.github.lwphk.pagehelper.plugins.PaginationResultSetHandlerInterceptor" />
		<plugin
			interceptor="com.github.lwphk.pagehelper.plugins.PaginationStatementHandlerInterceptor" />
	</plugins>
</configuration>

3.------------正常配置sqlSessionFactory--------


	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		//mybatis-config.xml路径
		sqlSessionFactoryBean.setConfigLocation("mybatis-config.xml");
		//xml文件路径
		sqlSessionFactoryBean.setMapperLocations("classpath:mybatis");
		return sqlSessionFactoryBean;
	}

=================================

使用示例见:pagehelper-demo.zip



