package com.rabo.statementProcessor.model;

import java.math.BigDecimal;
import java.util.Objects;

public class StatementRecord {
	
	private Long reference;
	private String accountNumber;
	private String description;
	private BigDecimal startBalance;
	private BigDecimal mutation;
	private BigDecimal endBalance;
	public Long getReference() {
		return reference;
	}
	public void setReference(Long reference) {
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
	public BigDecimal getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}
	public BigDecimal getMutation() {
		return mutation;
	}
	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}
	public BigDecimal getEndBalance() {
		return endBalance;
	}
	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}
	
	
	@Override
	public int hashCode() {
		return reference != null ? reference.hashCode() : 0;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatementRecord other = (StatementRecord) obj;
		return Objects.equals(reference, other.reference);
	}
	
	

}
