package com.epam.apartmentBooking.service;

import java.util.List;

import com.epam.apartmentBooking.model.Apartment;

public interface IApartmentService {

	List<Apartment> findAll();

	long create(Apartment apartment);

	void delete(Long id);

	long update(Apartment apartment);

	Apartment findById(long id);

}
