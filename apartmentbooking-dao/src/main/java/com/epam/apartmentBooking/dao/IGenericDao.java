package com.epam.apartmentBooking.dao;

import java.util.List;

public interface IGenericDao<E, K> {
	List<E> findAll();

	long create(E entity);

	void delete(K id);

	long update(E entity);
}
