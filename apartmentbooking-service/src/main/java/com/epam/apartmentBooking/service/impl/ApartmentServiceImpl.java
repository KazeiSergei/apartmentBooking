package com.epam.apartmentBooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.apartmentBooking.dao.IApartmentDao;
import com.epam.apartmentBooking.model.Apartment;
import com.epam.apartmentBooking.service.IApartmentService;

@Service("apartmentService")
@Transactional
public class ApartmentServiceImpl implements IApartmentService {

	@Autowired
	private IApartmentDao apartmentDao;

	public List<Apartment> findAll() {
		return apartmentDao.findAll();
	}

	public long create(Apartment apartment) {
		return apartmentDao.create(apartment);
	}

	public void delete(Long id) {
		apartmentDao.delete(id);
	}

	public long update(Apartment apartment) {
		return apartmentDao.update(apartment);
	}

	public Apartment findById(long id) {
		return apartmentDao.findById(id);
	}

}
