package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/expenseReport")
public class ExpenseReportServlet extends HttpServlet {

	FERService ferService = new FERServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		String expenseType = req.getParameter("ExpenseType");
		String fromDate = req.getParameter("From date");
		String toDate = req.getParameter("To date");

		List<Expense> expenses = ferService.expenseReport(userId, expenseType, fromDate, toDate);

		PrintWriter out = resp.getWriter();

		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		
		if (expenses.isEmpty()) {

			out.println("No expenses are found........");

		} else {

			out.println("Expenses are found........");

			LayoutUtil.displayFooter(req, resp);
		}

	}

}
