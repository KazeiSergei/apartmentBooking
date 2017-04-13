package com.epam.apartmentBooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.apartmentBooking.dao.IApartmentTypeDao;
import com.epam.apartmentBooking.model.ApartmentType;
import com.epam.apartmentBooking.service.IApartmentTypeService;

public class ApartmentTypeServiceImpl implements IApartmentTypeService {

	@Autowired
	private IApartmentTypeDao iApartmentTypeDao;

	public List<ApartmentType> findAll() {
		return iApartmentTypeDao.findAll();
	}

}
