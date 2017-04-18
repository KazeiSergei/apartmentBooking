package com.epam.apartmentBooking.dao;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Main {

	private static SecureRandom random = new SecureRandom();

	public static void main(String[] args) {
		// ApplicationContext applicationContext = new
		// AnnotationConfigApplicationContext(BeenConfig.class);

		System.out.println(nextSessionId());

	}

	public static String nextSessionId() {
		return new BigInteger(50, random).toString(32);
	}

}
