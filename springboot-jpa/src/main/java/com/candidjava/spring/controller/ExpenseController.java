package com.candidjava.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.candidjava.spring.bean.Expense;
import com.candidjava.spring.service.ExpenseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "FER")
@RequestMapping(value = { "/expense" })
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;

	@ApiOperation(value = "getExpenseById")
	@GetMapping(value = "/{expenseId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Expense> getUserById(@PathVariable("expenseId") long expenseId) {
		System.out.println("Fetching User with expenseId " + expenseId);
		Expense expense = expenseService.findByexpenseId(expenseId);
		if (expense == null) {
			return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}

	@ApiOperation(value = "AddExpense")
	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createExpense(@RequestBody Expense expense, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Expense " + expense.getExpenseType());
		expenseService.createExpense(expense);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/expense/{expenseId}").buildAndExpand(expense.getExpenseId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "getExpeses")
	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Expense> getAllExpense() {
		List<Expense> tasks = expenseService.getExpense();
		return tasks;

	}

	@ApiOperation(value = "DeleteExpenes")
	@DeleteMapping(value = "/{expenseId}", headers = "Accept=application/json")
	public ResponseEntity<Expense> deleteExpense(@PathVariable("expenseId") long expenseId) {
		Expense expense = expenseService.findByexpenseId(expenseId);
		if (expense == null) {
			return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
		}
		expenseService.deleteExpenseByexpenseId(expenseId);
		return new ResponseEntity<Expense>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "updateExpense")
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateExpense(@RequestBody Expense currentExpense) {
		System.out.println("sd");
		Expense expense = expenseService.findByexpenseId(currentExpense.getExpenseId());
		if (expense == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		expenseService.update(currentExpense, currentExpense.getExpenseId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/expense/{expenseType}/{fromDate}/{toDate}")
	public ResponseEntity<List<Expense>> getExpenseReport(@PathVariable("expenseType") String expenseType,
			@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) {
		List<Expense> expenses = expenseService.getExpenseReport(expenseType, fromDate, toDate);
		return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
	}
}
