package com.epam.apartmentBooking.dao;

import com.epam.apartmentBooking.model.User;

/**
 * Created by Siarhei_Kazei on 4/7/2017.
 */
public interface IUserDao extends IGenericDao<User, Long> {
	User checkUser(User user);

	User findById(long id);
}
