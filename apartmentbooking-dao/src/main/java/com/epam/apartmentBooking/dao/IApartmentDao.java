package com.epam.apartmentBooking.dao;

import com.epam.apartmentBooking.model.Apartment;

import java.util.List;


public interface IApartmentDao extends IGenericDao<Apartment, Long> {
    Apartment findById(long id);
    List<Apartment> findAvailible();
}
