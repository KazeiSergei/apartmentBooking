package com.epam.apartmentBooking.dao;

import static org.h2.engine.Constants.UTF8;

import java.sql.SQLException;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.apartmentBooking.model.User;

public class JdbcUserDaoImplTest extends EntityDaoImplTest {

	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
	private static final String USER = "sa";
	private static final String PASSWORD = "";

	@Autowired
	IUserDao iUserDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("users.xml"));
		return dataSet;
	}

	@BeforeClass
	public static void createSchema() throws SQLException {
		RunScript.execute(JDBC_URL, USER, PASSWORD, "classpath:schema.sql", UTF8, false);
	}

	@Test
	public void getUserByIdTest() {
		Assert.assertNotNull(iUserDao.findById(2));
		Assert.assertEquals(iUserDao.findById(3).getName(), "Sergei");
		Assert.assertEquals(iUserDao.findById(3).getSurname(), "Terton");
	}

	@Test
	public void findAllUserTest() {
		Assert.assertEquals(iUserDao.findAll().size(), 2);
	}

	@Test
	public void deleteUserByIdTest() {
		iUserDao.delete((long) 2);
		Assert.assertEquals(iUserDao.findAll().size(), 1);
	}

	@Test
	public void insertUserTest() {
		iUserDao.create(getSampleUser(5, "Test", "Surname", "Test@mail.ru", "TestPassword"));
		Assert.assertEquals(iUserDao.findAll().size(), 3);
	}

	@Test
	public void updateUserTest() {
		iUserDao.update(getSampleUser(3, "UpdateName", "UpdateSurname", "UpdateTest@mail.ru", "UpdateTestPassword"));
		Assert.assertEquals(iUserDao.findById(3).getName(), "UpdateName");
	}

	@Test
	public void checkUserTest() {
		Assert.assertNotNull(iUserDao.checkUser(getSampleUser(2, "Anton", "Zaleski", "Zaleski@mail.ru", "11111111")));
		// Assert.assertNull(iUserDao.checkUser(getSampleUser(2,"Anton","Zaleski","Zaleski@mail.ru","111111111")));
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
