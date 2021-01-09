package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/displayReview")
public class DisplayReviewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		

		User user = (User) session.getAttribute("user");

		Address address = user.getAddress();
		address.setLineOne(req.getParameter("lineOne"));
		address.setLineTwo(req.getParameter("lineTwo"));
		address.setCity(req.getParameter("city"));
		address.setState(req.getParameter("state"));
		address.setPostalCode(req.getParameter("postalCode"));
		address.setCountry(req.getParameter("country"));
		
		PrintWriter out = resp.getWriter();
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		if (user == null) {
			out.println("User details are not found...");
		} else {
			
			out.println("<table align='center' border='1'>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>Review</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>First Name</td>");
			out.println("<td>");
			out.println(user.getFirstName());
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Middle Name</td>");
			out.println("<td>");
			out.println(user.getMiddleName());
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Last Name</td>");
			out.println("<td>");
			out.println(user.getLastName());
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Email</td>");
			out.println("<td>");
			out.println(user.getEmail());
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Mobile</td>");
			out.println("<td>");
			out.println(user.getMobile());
			out.println("</td>");
			out.println("</tr>");
			
			
			out.println("<tr>");
			out.println("<td>Line1</td>");
			out.println("<td>");
			out.println(address.getLineOne());
			out.println("</td>");
			out.println("</tr>");
		
			out.println("<tr>");
			out.println("<td>Line2</td>");
			out.println("<td>");
			out.println(address.getLineTwo());
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>City</td>");
			out.println("<td>");
			out.println(address.getCity());
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>State</td>");
			out.println("<td>");
			out.println(address.getState());
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Postal Code</td>");
			out.println("<td>");
			out.println(address.getPostalCode());
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Country</td>");
			out.println("<td>");
			out.println(address.getCountry());
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>");
			out.println("<input type = 'button' value = 'Update' onClick=\"javascript: submitForm('updateUser')\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			
		}
		LayoutUtil.displayFooter(req, resp);
	}
}
