package com.epam.apartmentBooking.service;


import com.epam.apartmentBooking.model.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();
    void delete(long id);
    long update(User user);
    User findById(long id);
    User signIn(User user);
    User signUp(User user);


}
