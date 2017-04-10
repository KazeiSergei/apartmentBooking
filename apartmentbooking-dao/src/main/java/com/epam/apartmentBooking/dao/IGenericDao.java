package com.epam.apartmentBooking.dao;

import java.util.List;

/**
 * Created by Siarhei_Kazei on 4/7/2017.
 */
public interface IGenericDao<E, K> {
    List<E> findAll();
    long create(E entity);
    boolean delete(K id);
    long update(E entity);
}
