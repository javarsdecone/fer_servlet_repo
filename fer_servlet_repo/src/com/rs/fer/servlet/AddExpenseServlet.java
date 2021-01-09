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

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/addExpense")

public class AddExpenseServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		
		Expense expense = new Expense();
		expense.setType(req.getParameter("expensetype"));
		expense.setDate(req.getParameter("date"));
		expense.setPrice(Float.parseFloat(req.getParameter("price")));
		expense.setNumberOfItems(Integer.parseInt(req.getParameter("numberOfItems")));
		expense.setTotal(Float.parseFloat(req.getParameter("total")));
		expense.setBywhom(req.getParameter("BYwhom"));
		expense.setUserId(userId);

		boolean isaddExpense = ferService.addExpense(expense);

		PrintWriter out = resp.getWriter();
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		if (isaddExpense) {
			out.println("Expense added is sucessfull");
		} else {
			out.println("expense added is  Failed");
		}
		LayoutUtil.displayFooter(req, resp);
	}

	@Override
	public void destroy() {
		ferService = null;
	}
}
