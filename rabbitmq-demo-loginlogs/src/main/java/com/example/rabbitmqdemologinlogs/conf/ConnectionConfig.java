package com.example.rabbitmqdemologinlogs.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = ConnectionConfig.PACKAGE_LOCATION, sqlSessionFactoryRef = "sqlSessionFactory")
public class ConnectionConfig {

    public final static String MAPPER_LOCATION = "classpath:mapper/*.xml";
    public final static String PACKAGE_LOCATION = "com.example.rabbitmqdemologinlogs.dao";

    @Value("${mysql.datasource.url}")
    private String url;

    @Value("${mysql.datasource.data-username}")
    private String user;

    @Value("${mysql.datasource.data-password}")
    private String password;

    @Value("${mysql.datasource.driver-class-name}")
    private String driver;

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ConnectionConfig.MAPPER_LOCATION));

        return sqlSessionFactoryBean.getObject();
    }
}

