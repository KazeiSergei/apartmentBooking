package com.epam.apartmentBooking.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.apartmentBooking.model.Apartment;

public class JdbcApartmentDaoImplTest extends EntityDaoImplTest {

	@Autowired
	IApartmentDao apartmentDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet[] dataSets = new IDataSet[] { new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("apartmentType.xml")), new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("city.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("apartments.xml")) };
		return new CompositeDataSet(dataSets);
	}

	@Test
	public void findApartmentByIdTest() {
		Assert.assertNotNull(apartmentDao.findById(2));
	}

	@Test
	public void findAllApartmentTest() {
		Assert.assertEquals(apartmentDao.findAll().size(), 2);
	}

	@Test
	public void deleteApartmentByIdTest() {
		apartmentDao.delete((long) 2);
		Assert.assertEquals(apartmentDao.findAll().size(), 1);
	}

	@Test
	public void insertApartmentTest() {
		apartmentDao.create(getSampleApartment(10, "TestApartment", "TestDescription", 8, 100, 1, 2));
		Assert.assertEquals(apartmentDao.findAll().size(), 3);
	}

	@Test
	public void updateApartmentTest() {
		apartmentDao.update(getSampleApartment(2, "TestApartment", "TestDescription", 8, 100, 2, 1));
		Assert.assertEquals(apartmentDao.findById(2), getSampleApartment(2, "TestApartment", "TestDescription", 8, 100, 2, 1));
	}

	public Apartment getSampleApartment(long id, String name, String description, int guestCount, int costPerDay, long apartmentTypeId, long cityId) {
		Apartment apartment = new Apartment();
		apartment.setId(id);
		apartment.setName(name);
		apartment.setDescription(description);
		apartment.setGuestCount(guestCount);
		apartment.setCostPerDay(costPerDay);
		apartment.setApartmentTypeId(apartmentTypeId);
		apartment.setCityId(cityId);
		return apartment;
	}
}
