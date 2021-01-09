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
import com.rs.fer.util.LayoutUtil;

@WebServlet("/displayContactInfo")
public class DisplayContactInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		

		User user = (User) session.getAttribute("user");
		
		user.setFirstName(req.getParameter("firstName"));
		user.setMiddleName(req.getParameter("middleName"));
		user.setLastName(req.getParameter("lastName"));
		
		PrintWriter out = resp.getWriter();
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		if (user == null) {
			out.println("User details are not found...");
		} else {

			out.println("<table align='center' border='1'>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>Contact Info</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Email</td>");
			out.println("<td>");
			out.println("<input type='text' name='email' value='"+user.getEmail()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Mobile</td>");
			out.println("<td>");
			out.println("<input type='text' name='mobile' value='"+user.getMobile()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>");
			out.println("<input type = 'button' value = 'Next' onClick=\"javascript: submitForm('displayAddressInfo')\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			
		}
		LayoutUtil.displayFooter(req, resp);
	}
}
