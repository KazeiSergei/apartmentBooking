package com.epam.apartmentBooking.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JdbcApartmentTypeDaoImlTest extends EntityDaoImplTest {

	@Autowired
	IApartmentTypeDao apartmentTypeDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("apartmentType.xml"));
	}

	@Test
	public void findAllApartmentTypeTest() {
		Assert.assertEquals(apartmentTypeDao.findAll().size(), 2);
	}

}
