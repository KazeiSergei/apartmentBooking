package com.epam.apartmentBooking.dao;

import java.util.Date;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.apartmentBooking.model.City;

public class JdbcApartmentFilterDaoImplTest extends EntityDaoImplTest {

	@Autowired
	private IApartmentFilterDao apartmentFilterDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet[] dataSets = new IDataSet[] { new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("apartmentType.xml")), new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("city.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("apartments.xml")), new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("applications.xml")) };
		return new CompositeDataSet(dataSets);
	}

	@Test
	public void findApartmentByCity() {
		Assert.assertEquals(apartmentFilterDao.findApartmentByCity(getSampleCity(1, "Minsk")).size(), 1);
	}

	@Test
	public void findAvailableApartment() {
		Assert.assertEquals(apartmentFilterDao.findAvailableApartment(getSampleCity(1, "Minsk"), 1, getDateCheckIn(), getDateCheckOut()).size(), 1);
	}

	public City getSampleCity(long id, String name) {
		City city = new City();
		city.setId(id);
		city.setName(name);
		return city;
	}

	public Date getDateCheckIn() {
		return new Date(2015, 11, 9);
	}

	public Date getDateCheckOut() {
		return new Date(2015, 11, 17);
	}

}
