package com.epam.apartmentBooking.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.apartmentBooking.dao.IApartmentFilterDao;
import com.epam.apartmentBooking.model.ApartmentFilter;
import com.epam.apartmentBooking.model.City;
import com.epam.apartmentBooking.service.IApartmentFilterService;

public class ApartmentFilterServiceImpl implements IApartmentFilterService {

	@Autowired
	IApartmentFilterDao apartmentFilterDao;

	@Override
	public List<ApartmentFilter> findApartmentByCity(City city) {
		return apartmentFilterDao.findApartmentByCity(city);
	}

	@Override
	public List<ApartmentFilter> findAvailableApartment(City city, int guest, Date checkIn, Date checkOut) {
		return apartmentFilterDao.findAvailableApartment(city, guest, checkIn, checkOut);
	}

}
