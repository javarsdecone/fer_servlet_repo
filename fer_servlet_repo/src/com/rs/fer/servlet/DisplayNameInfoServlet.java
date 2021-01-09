package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/displayNameInfo")
public class DisplayNameInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		

		FERService ferService = new FERServiceImpl();
		User user = ferService.getUser(userId);

		PrintWriter out = resp.getWriter();
		session.setAttribute("user", user);
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		if (user == null) {
			out.println("User details are not found...");
		} else {

			out.println("<table align='center' border='1'>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>Name Info</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>First Name</td>");
			out.println("<td>");
			out.println("<input type='text' name='firstName' value='"+user.getFirstName()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Middle Name</td>");
			out.println("<td>");
			out.println("<input type='text' name='middleName' value='"+user.getMiddleName()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Last Name</td>");
			out.println("<td>");
			out.println("<input type='text' name='lastName' value='"+user.getLastName()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>");
			out.println("<input type = 'button' value = 'Next' onClick=\"javascript: submitForm('displayContactInfo')\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			
		}
		LayoutUtil.displayFooter(req, resp);
	}
}
