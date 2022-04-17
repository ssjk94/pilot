package com.pilot.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(value = "com.pilot.mapper.primary", sqlSessionFactoryRef = "pSqlSessionFactory")
public class PrimaryDbConfig {

	@Bean(name = "pDataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.primary.datasource")
	public DataSource pDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "pSqlSessionFactory")
	@Primary
	public SqlSessionFactory pSqlSessionFactory(@Qualifier("pDataSource") DataSource pDataSource,
			ApplicationContext applicationContext) throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(pDataSource);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "pSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate pSqlSessionTemplate(SqlSessionFactory pSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(pSqlSessionFactory);
	}

}
