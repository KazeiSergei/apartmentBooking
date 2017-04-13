package com.epam.apartmentBooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.apartmentBooking.dao.ICityDao;
import com.epam.apartmentBooking.model.City;
import com.epam.apartmentBooking.service.ICityService;

public class CityServiceImpl implements ICityService {

	@Autowired
	private ICityDao iCityDao;

	public List<City> findAll() {
		return iCityDao.findAll();
	}

}
