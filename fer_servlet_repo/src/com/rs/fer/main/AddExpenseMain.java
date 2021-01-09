package com.rs.fer.main;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class AddExpenseMain {

	public static void main(String[] args) {

		Expense expense = new Expense();
		expense.setType("tea");
		expense.setDate("02-12-2020");
		expense.setPrice(10);
		expense.setNumberOfItems(3);
		expense.setTotal(30);
		expense.setBywhom("Me");
		expense.setUserId(1);

		FERService ferService = new FERServiceImpl();
		boolean isAddExpense = ferService.addExpense(expense);

		if (isAddExpense) {
			System.out.println(" Expensed added successfully...");
		} else {
			System.out.println(" Expense add  failed...");
		}

	}
}
