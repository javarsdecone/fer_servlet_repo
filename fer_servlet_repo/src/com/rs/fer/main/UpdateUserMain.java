package com.rs.fer.main;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class UpdateUserMain {

	public static void main(String[] args) {
		int userId = 33;

		FERService ferService = new FERServiceImpl();
		User user = ferService.getUser(userId);

		user.setFirstName("f33");
		user.setMiddleName("m33");
		user.setLastName("l433");

		user.setEmail("e533@gmail.com");
		user.setMobile("433443633");

		Address address = user.getAddress();
		address.setLineOne("1234");
		address.setLineTwo("l2");
		address.setCity("c3");
		address.setState("s4");
		address.setPostalCode("p5");
		address.setCountry("c6");

		boolean isUpdateUser = ferService.updateUser(user);

		if (isUpdateUser) {
			System.out.println("User updated successfully...");
		} else {
			System.out.println("User update failed...");
		}

	}

}
