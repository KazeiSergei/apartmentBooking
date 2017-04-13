package com.epam.apartmentBooking;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.apartmentBooking.configuration.BeenConfig;
import com.epam.apartmentBooking.dao.impl.JdbcUserDaoImpl;
import com.epam.apartmentBooking.model.User;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ApplicationContext context = new AnnotationConfigApplicationContext(BeenConfig.class);
		JdbcUserDaoImpl jdbcUserDao = (JdbcUserDaoImpl) context.getBean("jdbcUserDao");
		for (User u : jdbcUserDao.findAll()) {
			System.out.println(u.getId());
			System.out.println(u.getName());
			System.out.println(u.getSurname());
			System.out.println(u.getEmail());
			System.out.println(u.getPassword());
		}
		User user = new User();
		user.setName("Artem");
		user.setSurname("Zaleski");
		user.setEmail("asdsad@mail.ru");
		user.setPassword("werfwefrew");
		// jdbcUserDao.create(user);
		// System.out.println(jdbcUserDao.create(user));
		// jdbcUserDao.delete((long)2);
		System.out.println(jdbcUserDao.findById(1));
	}
}