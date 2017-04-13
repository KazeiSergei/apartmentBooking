package com.epam.apartmentBooking.service;


import com.epam.apartmentBooking.model.ApartmentType;

import java.util.List;

public interface IApartmentTypeService {
    List<ApartmentType> findAll();
}
