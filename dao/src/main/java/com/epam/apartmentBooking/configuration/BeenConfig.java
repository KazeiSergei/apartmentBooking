package com.epam.apartmentBooking.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class BeenConfig {

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();

		return basicDataSource;

	}

	@Bean(name = "namedParameterJdbcTemplate")
	public NamedParameterJdbcOperations namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

}
