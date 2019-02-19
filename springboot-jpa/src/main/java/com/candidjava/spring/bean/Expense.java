package com.candidjava.spring.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ExpenseInfo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Expense {

	@Id

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long expenseId;

	@Column(name = "expenseType")
	private String expenseType;

	@Column(name = "date")
	private String date;

	@Column(name = "price")
	private String price;

	@Column(name = "noOfItems")
	private String noOfItems;

	@Column(name = "totalAccount")
	private String totalAccount;

	@Column(name = "byWhom")
	private String byWhom;

	@Column(name = "userId")
	private int userId;

	

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(String noOfItems) {
		this.noOfItems = noOfItems;
	}

	public String getTotalAccount() {
		return totalAccount;
	}

	public void setTotalAccount(String totalAccount) {
		this.totalAccount = totalAccount;
	}

	public String getByWhom() {
		return byWhom;
	}

	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	

}