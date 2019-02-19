package com.candidjava.spring.service;

import java.util.List;

import com.candidjava.spring.bean.Expense;

public interface ExpenseService {

	public void createExpense(Expense expense);

	public List<Expense> getExpense();

	public Expense findByexpenseId(long expenseId);

	public Expense update(Expense expense, Long l);

	public void deleteExpenseByexpenseId(long expenseId);
	
	List<Expense> getExpenseReport(String type, String fromDate, String toDate);
	
}
