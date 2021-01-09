package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/displayEditExpense")
public class DisplayEditExpenseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int expenseId = Integer.parseInt(req.getParameter("expenseId"));

		FERService ferService = new FERServiceImpl();
		Expense expense = ferService.getExpense(expenseId);

		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		session.setAttribute("expenseId", expenseId);
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));
		if (expense == null) {
			out.println("Expense details are not found...");
		} else {

			out.println("<table align='center' border='1'>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>Edit Expense</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Expense Type</td>");
			out.println("<td>");
			out.println("<input type='text' name='expenseType' value='"+expense.getType()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Date</td>");
			out.println("<td>");
			out.println("<input type='text' name='date' value='"+expense.getDate()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Price</td>");
			out.println("<td>");
			out.println("<input type='text' name='price' value='"+expense.getPrice()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Number of Items</td>");
			out.println("<td>");
			out.println("<input type='text' name='numberOfItems' value='"+expense.getNumberOfItems()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Total</td>");
			out.println("<td>");
			out.println("<input type='text' name='total' value='"+expense.getTotal()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Bywhom</td>");
			out.println("<td>");
			out.println("<input type='text' name='bywhom' value='"+expense.getBywhom()+"'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>");
			out.println("<input type = 'button' value = 'Edit Expense' onClick=\"javascript: submitForm('editExpense')\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			
		}
		LayoutUtil.displayFooter(req, resp);
	}
}
