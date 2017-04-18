package com.epam.apartmentBooking.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.apartmentBooking.dao.IApartmentFilterDao;
import com.epam.apartmentBooking.model.ApartmentFilter;
import com.epam.apartmentBooking.model.ApartmentType;
import com.epam.apartmentBooking.model.City;
import com.epam.apartmentBooking.service.impl.ApartmentFilterServiceImpl;

public class ApartmentFilterServiceImplTest {

	@Mock
	IApartmentFilterDao apartmentFilterDao;

	@InjectMocks
	ApartmentFilterServiceImpl apartmentFilterService;

	private List<ApartmentFilter> apartmentFilters = new ArrayList<ApartmentFilter>();
	private Date checkIn = new Date();
	private Date checkOut = new Date();

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		apartmentFilters = getApartmentFilterList();
		checkIn = new Date(2013);
		checkOut = new Date(2012);
	}

	@Test
	public void findAllByCity() {
		when(apartmentFilterDao.findApartmentByCity(any(City.class))).thenReturn(apartmentFilters);
		Assert.assertEquals(apartmentFilterService.findApartmentByCity(any(City.class)), apartmentFilters);
	}

	@Test
	public void findAvailableApartment() {
		when(apartmentFilterDao.findAvailableApartment(apartmentFilters.get(0).getCity(), 5, checkIn, checkOut)).thenReturn(apartmentFilters);
		Assert.assertEquals(apartmentFilterService.findAvailableApartment(apartmentFilters.get(0).getCity(), 5, checkIn, checkOut).size(), 2);
	}

	public List<ApartmentFilter> getApartmentFilterList() {
		ApartmentFilter apartmentFilter1 = new ApartmentFilter();
		apartmentFilter1.setId(1);
		apartmentFilter1.setName("Apartment1");
		apartmentFilter1.setDescription("Good apartment");
		apartmentFilter1.setGuestCount(5);
		apartmentFilter1.setCostPerDay(55);
		ApartmentType apartmentType1 = new ApartmentType();
		apartmentType1.setId(1);
		apartmentType1.setApartmentType("test1");
		apartmentFilter1.setApartmentType(apartmentType1);
		City city1 = new City();
		city1.setId(1);
		city1.setName("Minsk");
		apartmentFilter1.setCity(city1);

		ApartmentFilter apartmentFilter2 = new ApartmentFilter();
		apartmentFilter2.setId(2);
		apartmentFilter2.setName("Apartment2");
		apartmentFilter2.setDescription("Good apartment2");
		apartmentFilter2.setGuestCount(2);
		apartmentFilter2.setCostPerDay(100);
		ApartmentType apartmentType2 = new ApartmentType();
		apartmentType2.setId(1);
		apartmentType2.setApartmentType("test2");
		apartmentFilter2.setApartmentType(apartmentType2);
		City city2 = new City();
		city2.setId(2);
		city2.setName("London");
		apartmentFilter2.setCity(city2);

		apartmentFilters.add(apartmentFilter1);
		apartmentFilters.add(apartmentFilter2);

		return apartmentFilters;
	}

}
