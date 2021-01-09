package com.rs.fer.main;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class LoginMain {

	public static void main(String[] args) {
		
		String username = "ram";
		String password = "";
		
		FERService ferService = new FERServiceImpl();
		boolean isValidUser = ferService.login(username, password);
		
		if(isValidUser) {
			System.out.println("User is valid...");
		}
		else {
			System.out.println("User is not valid...");
		}

	}

}
