package com.epam.apartmentBooking.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.apartmentBooking.dao.IUserDao;
import com.epam.apartmentBooking.model.User;
import com.epam.apartmentBooking.service.impl.UserServiceImpl;

public class UserServiceTest {

	@Mock
	IUserDao userDao;

	@InjectMocks
	UserServiceImpl userService;

	private List<User> userList = new ArrayList<User>();

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userList = getUserList();
	}

	@Test
	public void findAll() {
		when(userDao.findAll()).thenReturn(userList);
		Assert.assertEquals(userService.findAll(), userList);
	}

	@Test
	public void delete() {
		doNothing().when(userDao).delete(anyLong());
		userService.delete(anyLong());
		userService.delete(anyLong());
		verify(userDao, atLeast(2)).delete(anyLong());
	}

	@Test
	public void update() {
		User u = userList.get(1);
		when(userDao.update(any(User.class))).thenReturn(1L);
		userService.update(u);
		verify(userDao, atLeastOnce()).update(any(User.class));
	}

	@Test
	public void findById() {
		User u = userList.get(0);
		when(userDao.findById(1)).thenReturn(u);
		Assert.assertEquals(userService.findById(1), u);
	}

	@Test
	public void signInTrue() {
		User u = userList.get(0);
		when(userDao.checkUser(u)).thenReturn(true);
		when(userDao.findUserByEmail(u)).thenReturn(u);
		Assert.assertEquals(userService.signIn(u), u);
	}

	@Test
	public void signInFalse() {
		User u = userList.get(0);
		when(userDao.checkUser(u)).thenReturn(false);
		when(userDao.findUserByEmail(u)).thenReturn(u);
		userService.signIn(u);
		verify(userDao, never()).findUserByEmail(u);
	}

	@Test
	public void signUpFalse() {
		User u = userList.get(0);
		when(userDao.checkEmail(u)).thenReturn(false);
		String password = u.getPassword();
		u.setPassword(DigestUtils.md2Hex(password));
		when(userDao.create(u)).thenReturn(1L);
		when(userDao.findUserByEmail(u)).thenReturn(u);
		Assert.assertEquals(userService.signUp(u), u);
	}

	@Test
	public void signUpTrue() {
		User u = userList.get(0);
		when(userDao.checkEmail(u)).thenReturn(true);
		Assert.assertNull(userService.signUp(u));
	}

	public List<User> getUserList() {
		User user1 = new User();
		user1.setId(1);
		user1.setName("Name1");
		user1.setSurname("SurName1");
		user1.setEmail("1@email.ru");
		user1.setPassword("password1");

		User user2 = new User();
		user2.setId(2);
		user2.setName("Name2");
		user2.setSurname("SurName2");
		user2.setEmail("2@email.ru");
		user2.setPassword("password2");

		User user3 = new User();
		user3.setId(3);
		user3.setName("Name3");
		user3.setSurname("SurName3");
		user3.setEmail("3@email.ru");
		user3.setPassword("password3");

		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		return userList;
	}

}
