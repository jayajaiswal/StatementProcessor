package com.rabo.statementProcessor.model;
import com.opencsv.bean.CsvBindByName;


public class CsvStatementData {
	
	@CsvBindByName
	private String reference;
	
	@CsvBindByName(column = "Account Number")
	private String accountNumber;
	

	private String description;
	
	@CsvBindByName(column = "Start Balance")
	private String startBalance;
	
	@CsvBindByName
	private String mutation;
	
	@CsvBindByName(column = "End Balance")
	private String endBalance;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(String startBalance) {
		this.startBalance = startBalance;
	}

	public String getMutation() {
		return mutation;
	}

	public void setMutation(String mutation) {
		this.mutation = mutation;
	}

	public String getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(String endBalance) {
		this.endBalance = endBalance;
	}

	@Override
	public String toString() {
		return reference +" - " + description; 
	}

	
}
