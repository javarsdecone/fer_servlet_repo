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
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/displayAddressInfo")
public class DisplayAddressInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		

		User user = (User) session.getAttribute("user");

		user.setEmail(req.getParameter("email"));
		user.setMobile(req.getParameter("mobile"));
		
		PrintWriter out = resp.getWriter();
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		if (user == null) {
			out.println("User details are not found...");
		} else {
			Address address = user.getAddress();
			
			out.println("<table align='center' border='1'>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>Address Info</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Line1</td>");
			out.println("<td>");
			out.println("<input type='text' name='lineOne' value='"+address.getLineOne()+"'>");
			out.println("</td>");
			out.println("</tr>");
		
			out.println("<tr>");
			out.println("<td>Line2</td>");
			out.println("<td>");
			out.println("<input type='text' name='lineTwo' value='"+address.getLineTwo()+"'>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>City</td>");
			out.println("<td>");
			out.println("<input type='text' name='city' value='"+address.getCity()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>State</td>");
			out.println("<td>");
			out.println("<input type='text' name='state' value='"+address.getState()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Postal Code</td>");
			out.println("<td>");
			out.println("<input type='text' name='postalCode' value='"+address.getPostalCode()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Country</td>");
			out.println("<td>");
			out.println("<input type='text' name='country' value='"+address.getCountry()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>");
			out.println("<input type = 'button' value = 'Review' onClick=\"javascript: submitForm('displayReview')\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			
		}
		LayoutUtil.displayFooter(req, resp);
	}
}
