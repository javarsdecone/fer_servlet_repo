package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/registration")

public class RegistrationServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		user.setFirstName(req.getParameter("firstName"));
		user.setMiddleName(req.getParameter("middleName"));
		user.setLastName(req.getParameter("lastName"));
		user.setEmail(req.getParameter("email"));
		user.setUsername(req.getParameter("userName"));
		user.setPassword(req.getParameter("password"));
		user.setMobile(req.getParameter("mobileNumber"));

		boolean isRegister = ferService.registration(user);

		PrintWriter out = resp.getWriter();
		String nextPath = "";
		if (isRegister) {
			out.println("User Registration is done sucessfully");
			nextPath = "Login.html";
		} else {
			out.println("User Registration is Failed. Please try again later");
			nextPath = "Registration.html";
		}
		req.getRequestDispatcher(nextPath).include(req, resp);
		
	}

	@Override
	public void destroy() {
		ferService = null;
	}
}
