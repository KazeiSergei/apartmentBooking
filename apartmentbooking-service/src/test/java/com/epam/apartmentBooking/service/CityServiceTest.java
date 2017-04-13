package com.epam.apartmentBooking.service;

import com.epam.apartmentBooking.dao.ICityDao;
import com.epam.apartmentBooking.model.City;
import com.epam.apartmentBooking.service.impl.CityServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class CityServiceTest {

    @Mock
    ICityDao cityDao;

    @InjectMocks
    CityServiceImpl cityService;

    @Spy
    List<City> cityList = new ArrayList<City>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cityList = getCityList();
    }

    @Test
    public void findAll() {
        when(cityDao.findAll()).thenReturn(cityList);
        Assert.assertEquals(cityService.findAll(), cityList);
    }

    public List<City> getCityList() {
        City city1 = new City();
        city1.setId(1);
        city1.setName("Minsk");

        City city2 = new City();
        city2.setId(2);
        city2.setName("Grodno");

        City city3 = new City();
        city3.setId(3);
        city3.setName("London");

        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);
        return cityList;
    }

}
