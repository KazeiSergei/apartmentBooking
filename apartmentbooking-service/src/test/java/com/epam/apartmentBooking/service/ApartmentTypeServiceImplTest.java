package com.epam.apartmentBooking.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.apartmentBooking.dao.IApartmentTypeDao;
import com.epam.apartmentBooking.model.ApartmentType;
import com.epam.apartmentBooking.service.impl.ApartmentTypeServiceImpl;

public class ApartmentTypeServiceImplTest {

	@Mock
	IApartmentTypeDao jdbcApartmentTypeDao;

	@InjectMocks
	ApartmentTypeServiceImpl apartmentTypeService;

	private List<ApartmentType> apartmentTypes = new ArrayList<ApartmentType>();

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		apartmentTypes = getApartmentTypeList();
	}

	@Test
	public void findAll() {
		when(jdbcApartmentTypeDao.findAll()).thenReturn(apartmentTypes);
		Assert.assertEquals(apartmentTypeService.findAll(), apartmentTypes);
	}

	public List<ApartmentType> getApartmentTypeList() {
		ApartmentType apartmentType1 = new ApartmentType();
		apartmentType1.setId(1);
		apartmentType1.setApartmentType("Entire home");

		ApartmentType apartmentType2 = new ApartmentType();
		apartmentType2.setId(1);
		apartmentType2.setApartmentType("Private room");

		ApartmentType apartmentType3 = new ApartmentType();
		apartmentType3.setId(1);
		apartmentType3.setApartmentType("Shared room");

		apartmentTypes.add(apartmentType1);
		apartmentTypes.add(apartmentType1);
		apartmentTypes.add(apartmentType1);
		return apartmentTypes;
	}
}
