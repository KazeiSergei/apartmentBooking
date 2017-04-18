package com.epam.apartmentBooking.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.apartmentBooking.dao.IUserDao;
import com.epam.apartmentBooking.model.User;
import com.epam.apartmentBooking.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	public List<User> findAll() {
		return userDao.findAll();
	}

	public void delete(long id) {
		userDao.delete(id);
	}

	public long update(User user) {
		return userDao.update(user);
	}

	public User findById(long id) {
		return userDao.findById(id);
	}

	public User signIn(User user) {
		if (!userDao.checkUser(user)) {
			return null;
		}
		return userDao.findUserByEmail(user);
	}

	public User signUp(User user) {
		if (userDao.checkEmail(user)) {
			return null;
		}
		String password = user.getPassword();
		user.setPassword(DigestUtils.md2Hex(password));
		userDao.create(user);
		return userDao.findUserByEmail(user);
	}

	public User editPassword(User user, String newPassword) {
		if (!userDao.checkUser(user)) {
			return null;
		}
		user.setPassword(DigestUtils.md2Hex(newPassword));
		userDao.update(user);
		return user;
	}

	public boolean restorePassword(User user) {
		if (!userDao.checkEmail(user)) {
			return false;
		}
		user.setPassword(DigestUtils.md2Hex(new BigInteger(50, new SecureRandom()).toString(32)));
		userDao.update(user);
		return true;
	}

}
