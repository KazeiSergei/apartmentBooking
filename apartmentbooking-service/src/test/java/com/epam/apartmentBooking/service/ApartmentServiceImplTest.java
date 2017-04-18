package com.epam.apartmentBooking.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.apartmentBooking.dao.IApartmentDao;
import com.epam.apartmentBooking.model.Apartment;
import com.epam.apartmentBooking.service.impl.ApartmentServiceImpl;

public class ApartmentServiceImplTest {

	@Mock
	IApartmentDao apartmentDao;

	@InjectMocks
	ApartmentServiceImpl apartmentService;

	private List<Apartment> apartmentList = new ArrayList<Apartment>();

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		apartmentList = getApartmentList();
	}

	@Test
	public void findAll() {
		when(apartmentDao.findAll()).thenReturn(apartmentList);
		Assert.assertEquals(apartmentService.findAll(), apartmentList);
	}

	@Test
	public void delete() {
		doNothing().when(apartmentDao).delete(anyLong());
		apartmentService.delete(anyLong());
		apartmentService.delete(anyLong());
		verify(apartmentDao, atLeast(2)).delete(anyLong());
	}

	@Test
	public void update() {
		Apartment apartment = apartmentList.get(1);
		when(apartmentDao.update(any(Apartment.class))).thenReturn(1L);
		apartmentService.update(apartment);
		verify(apartmentDao, atLeastOnce()).update(any(Apartment.class));
	}

	@Test
	public void findById() {
		Apartment apartment = apartmentList.get(0);
		when(apartmentDao.findById(1)).thenReturn(apartment);
		Assert.assertEquals(apartmentService.findById(apartment.getId()), apartment);
	}

	public List<Apartment> getApartmentList() {
		Apartment apartment1 = new Apartment();
		apartment1.setId(1);
		apartment1.setName("Apartment1");
		apartment1.setDescription("Good apartment");
		apartment1.setGuestCount(5);
		apartment1.setCostPerDay(55);
		apartment1.setApartmentTypeId(1);
		apartment1.setCityId(1);

		Apartment apartment2 = new Apartment();
		apartment2.setId(2);
		apartment2.setName("Apartment2");
		apartment2.setDescription("Good apartment2");
		apartment2.setGuestCount(2);
		apartment2.setCostPerDay(100);
		apartment2.setApartmentTypeId(2);
		apartment2.setCityId(1);

		Apartment apartment3 = new Apartment();
		apartment3.setId(3);
		apartment3.setName("Apartment3");
		apartment3.setDescription("Good apartment3");
		apartment3.setGuestCount(1);
		apartment3.setCostPerDay(120);
		apartment3.setApartmentTypeId(1);
		apartment3.setCityId(2);

		apartmentList.add(apartment1);
		apartmentList.add(apartment2);
		apartmentList.add(apartment3);
		return apartmentList;
	}

}
