package com.epam.apartmentBooking.dao;

import java.util.Date;
import java.util.List;

import com.epam.apartmentBooking.model.ApartmentFilter;
import com.epam.apartmentBooking.model.City;

public interface IApartmentFilterDao {
	List<ApartmentFilter> findApartmentByCity(City city);

	List<ApartmentFilter> findAvailableApartment(City city, int guest, Date checkIn, Date checkOut);
}
