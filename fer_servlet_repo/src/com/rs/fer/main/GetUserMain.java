package com.rs.fer.main;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class GetUserMain {

	public static void main(String[] args) {
		int userId = 37;

		FERService ferService = new FERServiceImpl();
		User user = ferService.getUser(userId);

		if (user == null) {
			System.out.println("User is not found...");
		} else {
			System.out.println("First Name: " + user.getFirstName());
			System.out.println("Middle Name: " + user.getMiddleName());
			System.out.println("Last Name: " + user.getLastName());

			System.out.println("Email: " + user.getEmail());
			System.out.println("Mobile: " + user.getMobile());

			Address address = user.getAddress();
			System.out.println("Line1: " + address.getLineOne());
			System.out.println("Line2: " + address.getLineTwo());
			System.out.println("City: " + address.getCity());
			System.out.println("State: " + address.getState());
			System.out.println("Postal Code: " + address.getPostalCode());
			System.out.println("Country: " + address.getCountry());

		}

	}

}
