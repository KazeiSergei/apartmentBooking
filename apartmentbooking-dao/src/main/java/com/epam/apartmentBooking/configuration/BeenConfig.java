package com.epam.apartmentBooking.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;


@Configuration
@ComponentScan("com.epam.apartmentBooking")
@PropertySource(value = {"classpath:application.properties"})
public class BeenConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        basicDataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        basicDataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        basicDataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        basicDataSource.setInitialSize(20);
        basicDataSource.setMaxActive(30);
        return basicDataSource;

    }

    @Bean(name = "namedParameterJdbcTemplate")
    public NamedParameterJdbcOperations namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

}
