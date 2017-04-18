package com.epam.apartmentBooking.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JdbcCityDaoImplTest extends EntityDaoImplTest {

	@Autowired
	private ICityDao cityDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("city.xml"));
	}

	@Test
	public void findAllApartmentTypeTest() {
		Assert.assertEquals(cityDao.findAll().size(), 2);
	}
}
