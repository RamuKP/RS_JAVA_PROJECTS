package com.candidjava.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}
