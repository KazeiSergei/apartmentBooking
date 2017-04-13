package com.epam.apartmentBooking.dao;

import com.epam.apartmentBooking.model.City;


public interface ICityDao extends IGenericDao<City,Long> {
    City findById(long id);
}
