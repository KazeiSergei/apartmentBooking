
package com.epam.apartmentBooking.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.apartmentBooking.model.User;

public class JdbcUserDaoImplTest extends EntityDaoImplTest {

	@Autowired
	IUserDao userDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("users.xml"));
	}

	@Test
	public void getUserByIdTest() {
		Assert.assertNotNull(userDao.findById(1));
	}

	@Test
	public void findAllUserTest() {
		Assert.assertEquals(userDao.findAll().size(), 2);
	}

	@Test
	public void deleteUserByIdTest() {
		userDao.delete((long) 1);
		Assert.assertEquals(userDao.findAll().size(), 1);
	}

	@Test
	public void insertUserTest() {
		userDao.create(getSampleUser(5, "Test", "Surname", "Test@mail.ru", "TestPassword"));
		Assert.assertEquals(userDao.findAll().size(), 3);
	}

	@Test
	public void updateUserTest() {
		userDao.update(getSampleUser(2, "UpdateName", "UpdateSurname", "UpdateTest@mail.ru", "UpdateTestPassword"));
		Assert.assertEquals(userDao.findById(2), getSampleUser(2, "UpdateName", "UpdateSurname", "UpdateTest@mail.ru", "UpdateTestPassword"));
	}

	@Test
	public void checkUserTrueTest() {
		Assert.assertTrue(userDao.checkUser(getSampleUser(2, "Anton", "Zaleski", "Zaleski@mail.ru", "11111111")));
	}

	@Test
	public void checkUserFalseTest() {
		Assert.assertFalse(userDao.checkUser(getSampleUser(2, "Anton", "Zaleski", "1Zaleski@mail.ru", "111111111")));
	}

	@Test
	public void checkEmailTrueTest() {
		Assert.assertTrue(userDao.checkEmail(getSampleUser(2, "Anton", "Zaleski", "Zaleski@mail.ru", "11111111")));
	}

	@Test
	public void checkEmailFalseTest() {
		Assert.assertFalse(userDao.checkEmail(getSampleUser(2, "Anton", "Zaleski", "Zaleski@mail.ru1", "11111111")));
	}

	@Test
	public void findUserByEmailTrueTest() {
		Assert.assertNotNull(userDao.findUserByEmail(getSampleUser(2, "Anton", "Zaleski", "Zaleski@mail.ru", "11111111")));
	}

	@Test
	public void findUserByEmailFalseTest() {
		Assert.assertNull(userDao.findUserByEmail(getSampleUser(2, "Anton", "Zaleski", "Z1aleski@mail.ru", "11111111")));
	}

	public User getSampleUser(long id, String name, String surname, String email, String password) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);
		user.setPassword(password);
		return user;

	}

}
