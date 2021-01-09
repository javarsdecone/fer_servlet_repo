package com.rs.fer.main;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class RegistrationMain {

	public static void main(String[] args) {

		// 1. To get the input
		User user = new User();
		user.setFirstName("admin");
		user.setMiddleName("");
		user.setLastName("rs");
		user.setEmail("admin@rs.com");
		user.setUsername("admin");
		user.setPassword("rs");
		user.setMobile("24332434");

		// 2. To call the service for business execution
		FERService ferService = new FERServiceImpl();
		boolean isRegister = ferService.registration(user);

		// 3. To print the status
		if (isRegister) {
			System.out.println("User registration is successful...");
		} else {
			System.out.println("User registration is failed...");
		}
	}

}
