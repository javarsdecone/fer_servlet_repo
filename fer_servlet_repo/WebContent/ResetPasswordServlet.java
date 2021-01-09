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

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		int userId =  Integer.parseInt(session.getAttribute("userId").toString());
		
		boolean isResetPassword = ferService.resetPassword(userId, req.getParameter("newpassword"), req.getParameter("oldpassword"));
		
		PrintWriter out = resp.getWriter();
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		
		if (isResetPassword) {
			out.println(" password resetted successfully...");
		} else {
		
			out.println("reset password  is failed...");
			
			LayoutUtil.displayFooter(req, resp);
		}

	}
}
