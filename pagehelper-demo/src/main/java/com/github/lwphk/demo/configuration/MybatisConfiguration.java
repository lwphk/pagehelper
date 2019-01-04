package com.github.lwphk.demo.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//mybatis xxxMapper 包路径
@MapperScan(basePackages={"com.github.lwphk.demo.dao.mapper"},sqlSessionFactoryRef="sqlSessionFactory")
public class MybatisConfiguration{

	@Value("${mybatis.configLocation}")
	private String configlocation;
	
	@Value("${mybatis.mapperLocations}")
	private String mapperLocations;

	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		//mybatis-config.xml路径
		sqlSessionFactoryBean.setConfigLocation(resolver.getResource(configlocation));
		//xml文件路径
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
		
		return sqlSessionFactoryBean;
	}
	
	
	@Bean
    public DataSourceTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
