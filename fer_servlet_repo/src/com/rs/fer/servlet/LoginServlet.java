package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int userId = ferService.login(req.getParameter("userName"), req.getParameter("password"));

		PrintWriter out = resp.getWriter();
		if (userId > 0) {
			
			HttpSession session = req.getSession();
			session.setAttribute("username", req.getParameter("userName"));
			session.setAttribute("userId", userId);
			
			LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
			out.println("Welcome to the user: "+session.getAttribute("username"));
			LayoutUtil.displayFooter(req, resp);
			
		} else {
			out.println("Incorrect username/password. Please try again later");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
	}

	@Override
	public void destroy() {
		ferService = null;
	}
}
