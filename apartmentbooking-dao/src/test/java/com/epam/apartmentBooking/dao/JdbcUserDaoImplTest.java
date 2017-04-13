package com.epam.apartmentBooking.dao;

import com.epam.apartmentBooking.model.User;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.h2.engine.Constants.UTF8;


public class JdbcUserDaoImplTest extends EntityDaoImplTest {

    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @Autowired
    IUserDao userDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("users.xml"));
    }

    @BeforeClass
    public static void createSchema() throws SQLException {
        RunScript.execute(JDBC_URL,USER,PASSWORD,"classpath:user.sql",UTF8,false);
    }

    @Test
    public void getUserByIdTest() {
        Assert.assertNotNull(userDao.findById(2));
    }

    @Test
    public void findAllUserTest() {
        Assert.assertEquals(userDao.findAll().size(), 2);
    }

    @Test
    public void deleteUserByIdTest() {
        userDao.delete((long)2);
        Assert.assertEquals(userDao.findAll().size(), 1);
    }

    @Test
    public void insertUserTest() {
        userDao.create(getSampleUser(5,"Test","Surname","Test@mail.ru","TestPassword"));
        Assert.assertEquals(userDao.findAll().size(), 3);
    }

    @Test
    public void updateUserTest() {
       userDao.update(getSampleUser(3,"UpdateName","UpdateSurname","UpdateTest@mail.ru","UpdateTestPassword"));
        Assert.assertEquals(userDao.findById(3), getSampleUser(3,"UpdateName","UpdateSurname","UpdateTest@mail.ru","UpdateTestPassword"));
    }

    @Test
    public void checkUserTrueTest() {
        Assert.assertTrue(userDao.checkUser(getSampleUser(2,"Anton","Zaleski","Zaleski@mail.ru","11111111")));
    }

    @Test
    public void checkUserFalseTest() {
        Assert.assertFalse(userDao.checkUser(getSampleUser(2,"Anton","Zaleski","1Zaleski@mail.ru","111111111")));
    }

    @Test
    public void checkEmailTrueTest() {
        Assert.assertTrue(userDao.checkEmail(getSampleUser(2,"Anton","Zaleski","Zaleski@mail.ru","11111111")));
    }

    @Test
    public void checkEmailFalseTest() {
        Assert.assertFalse(userDao.checkEmail(getSampleUser(2,"Anton","Zaleski","Zaleski@mail.ru1","11111111")));
    }

    @Test
    public void findUserByEmailTrueTest() {
        Assert.assertNotNull(userDao.findUserByEmail(getSampleUser(2,"Anton","Zaleski","Zaleski@mail.ru","11111111")));
    }

    @Test
    public void findUserByEmailFalseTest() {
        Assert.assertNull(userDao.findUserByEmail(getSampleUser(2,"Anton","Zaleski","Z1aleski@mail.ru","11111111")));
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
