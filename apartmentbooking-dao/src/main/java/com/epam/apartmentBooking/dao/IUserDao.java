package com.epam.apartmentBooking.dao;

import com.epam.apartmentBooking.model.User;


public interface IUserDao extends IGenericDao<User, Long> {
    boolean checkUser(User user);
    boolean checkEmail(User user);
    User findById(long id);
    User findUserByEmail(User user);
}
