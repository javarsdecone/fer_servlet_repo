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

@WebServlet("/deleteExpense")

public class DeleteExpenseServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int expenseId = Integer.parseInt(req.getParameter("expenseId"));

		boolean isDeleteExpense = ferService.deleteExpense(expenseId);

		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		if (isDeleteExpense) {
			out.println("Expense deleted sucessfully");
		} else {
			out.println("expense deleted   Failed");
		}
		LayoutUtil.displayFooter(req, resp);
	}

	@Override
	public void destroy() {
		ferService = null;
	}
}
