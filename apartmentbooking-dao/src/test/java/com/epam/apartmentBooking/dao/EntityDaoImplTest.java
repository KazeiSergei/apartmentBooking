
package com.epam.apartmentBooking.dao;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import com.epam.apartmentBooking.configuration.BeenConfigTest;

@ContextConfiguration(classes = { BeenConfigTest.class })
public abstract class EntityDaoImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private DataSource dataSource;

	private static String userName = "C##TEST1";

	@BeforeMethod
	public void setUp() throws Exception {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource, userName);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}

	protected abstract IDataSet getDataSet() throws Exception;
}
