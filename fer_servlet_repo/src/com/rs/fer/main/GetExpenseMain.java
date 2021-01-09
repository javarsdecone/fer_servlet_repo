package com.rs.fer.main;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class GetExpenseMain {

	public static void main(String[] args) {

		int expenseId = 7;

		FERService ferService = new FERServiceImpl();
		Expense expense = ferService.getExpense(expenseId);
		
		if(expense == null) {
			System.out.println("Expense details are not found...");
		} else {
			System.out.println("Type: "+expense.getType());
			System.out.println("Date: "+expense.getDate());
			System.out.println("Price: "+expense.getPrice());
			System.out.println("Number of Items: "+expense.getNumberOfItems());
			System.out.println("Total: "+expense.getTotal());
			System.out.println("Bywhom: "+expense.getBywhom());
		}
	}

}
