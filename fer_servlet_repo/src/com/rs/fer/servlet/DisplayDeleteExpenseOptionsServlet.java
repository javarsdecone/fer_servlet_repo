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

@WebServlet("/displayDeleteExpenseOptions")
public class DisplayDeleteExpenseOptionsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		FERService ferService = new FERServiceImpl();
		List<Expense> expenses = ferService.getExpenses(userId);

		PrintWriter out = resp.getWriter();

		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		if (expenses.isEmpty()) {
			out.println("No expenses are found...");
		} else {

			out.println("Expense ID:");

			out.println("<select name='expenseId'>");
			out.println("	<option value=''>Please select Expense ID</option>");

			for (Expense expense : expenses) {
				int value = expense.getId();
				String desc = expense.getType() + ", " + expense.getDate() + ", " + expense.getTotal() + ", "
						+ expense.getBywhom();
				out.println("	<option value='" + value + "'>" + desc + "</option>");
			}

			out.println("</select>");

			out.println("<input type = 'button' value = 'Delete' onClick=\"javascript: submitForm('deleteExpense')\">");

		}
		LayoutUtil.displayFooter(req, resp);
	}
}
