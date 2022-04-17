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

@Configuration
@MapperScan(value = "com.pilot.mapper.sub", sqlSessionFactoryRef = "sSqlSessionFactory")
public class SubDbConfig {

	@Bean(name = "sDataSource")
	@ConfigurationProperties(prefix="spring.sub.datasource")
	public DataSource sDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "sSqlSessionFactory")
	public SqlSessionFactory sSqlSessionFactory(@Qualifier("sDataSource") DataSource sDataSource,
			ApplicationContext applicationContext) throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(sDataSource);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "sSqlSessionTemplate")
	public SqlSessionTemplate sSqlSessionTemplate(SqlSessionFactory sSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sSqlSessionFactory);
	}

}
