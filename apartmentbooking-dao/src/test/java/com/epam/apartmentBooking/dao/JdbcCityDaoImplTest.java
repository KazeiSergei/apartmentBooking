package com.epam.apartmentBooking.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.h2.engine.Constants.UTF8;

public class JdbcCityDaoImplTest extends EntityDaoImplTest {

    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @Autowired
    ICityDao cityDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("city.xml"));
    }

    @BeforeClass
    public static void createSchema() throws SQLException {
        RunScript.execute(JDBC_URL,USER,PASSWORD,"classpath:city.sql",UTF8,false);
    }

    @Test
    public void findAllApartmentTypeTest() {
        Assert.assertEquals(cityDao.findAll().size(), 2);
    }
}
