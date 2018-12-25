package com.jt.common.config;
import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.alibaba.druid.pool.DruidDataSource;
/**
 * 配置service,dao等相关对象
 * @author 速度
 */
@Configuration
@ComponentScan(value="com.jt",
excludeFilters={@Filter(classes={Controller.class,ControllerAdvice.class})})
@PropertySource("classpath:configs.properties")
@MapperScan("com.jt.**.dao")
public class AppRootConfig {
	//<bean id="" class="">   
	@Bean(value="dataSource",
		  initMethod="init",
		  destroyMethod="close") 
	@Lazy(false)
	public DataSource newDataSource(
		@Value("${jdbcDriver}")String driver,
		@Value("${jdbcUrl}")String url,
		@Value("${jdbcUser}")String username,
		@Value("${jdbcPassword}")String password){
		DruidDataSource ds=new DruidDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		//ds.setMaxActive(maxActive);
		//ds.setMinIdle(value);
		//......
		return ds;
	}
	/**整合SqlSessionFactoryBean对象
	 * 通过此对象创建SqlSessionFactory
	 * @throws IOException */
	@Bean("sqlSessionFactory")
	public SqlSessionFactoryBean newSqlSessionFactoryBean(
	  @Autowired DataSource dataSource) throws IOException{
		SqlSessionFactoryBean fBean=
		new SqlSessionFactoryBean();
		fBean.setDataSource(dataSource);
		Resource[] mapperLocations=
		new PathMatchingResourcePatternResolver()
		.getResources("classpath*:mapper/sys/*Mapper.xml");
		fBean.setMapperLocations(mapperLocations);
		return fBean;
	}
	/*@Bean
	public MapperScannerConfigurer newMapperScannerConfigurer(){
		MapperScannerConfigurer msc=new MapperScannerConfigurer();
		msc.setBasePackage("com.jt.**.dao");
		msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return msc;
	}*/
}