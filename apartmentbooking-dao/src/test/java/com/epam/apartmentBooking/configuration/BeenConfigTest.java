package com.epam.apartmentBooking.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.epam.apartmentBooking")
public class BeenConfigTest {

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1523:ORCL");
		driverManagerDataSource.setUsername("C##TEST1");
		driverManagerDataSource.setPassword("admin");
		return driverManagerDataSource;

	}

	@Bean(name = "namedParameterJdbcTemplate")
	public NamedParameterJdbcOperations namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

}
