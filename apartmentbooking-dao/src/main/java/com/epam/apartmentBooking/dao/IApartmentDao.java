package com.epam.apartmentBooking.dao;

import com.epam.apartmentBooking.model.Apartment;

public interface IApartmentDao extends IGenericDao<Apartment, Long> {
	Apartment findById(long id);
}
