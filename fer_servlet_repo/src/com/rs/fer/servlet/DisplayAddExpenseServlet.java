package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.util.LayoutUtil;

@WebServlet("/displayAddExpense")
public class DisplayAddExpenseServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();

		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));

		req.getRequestDispatcher("AddExpense.html").include(req, resp);

		LayoutUtil.displayFooter(req, resp);

	}
}
