package com.candidjava.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.Expense;
import com.candidjava.spring.repository.ExpenseRepository;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	ExpenseRepository expenseRepository;
	EntityManager entityManager;

	@Override
	public void createExpense(Expense expense) {
		expenseRepository.save(expense);

	}

	@Override
	public List<Expense> getExpense() {
		return (List<Expense>) expenseRepository.findAll();
	}

	@Override
	public Expense findByexpenseId(long expenseId) {
		return expenseRepository.findOne(expenseId);
	}
	@Override
	public void deleteExpenseByexpenseId(long expenseId) {
		expenseRepository.delete(expenseId);
	}

	@Override
	public List<Expense> getExpenseReport(String type, String fromDate, String toDate) {
		List<Expense> expenses = new ArrayList<Expense>();
		try {
			Query query = entityManager.createQuery("from Expense e where e.type='" + type + "'and e.date between '"
					+ fromDate + "'and '" + toDate + "'");
		/*	query.setParameter(0, type);
			query.setParameter(1, fromDate);
			query.setParameter(2, toDate);*/
			
			expenses = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return expenses;

	}

	@Override
	public Expense update(Expense expense, Long l) {
		// TODO Auto-generated method stub
		return null;
	}

}
