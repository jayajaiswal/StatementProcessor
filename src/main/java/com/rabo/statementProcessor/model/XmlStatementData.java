package com.rabo.statementProcessor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlStatementData {

	@JacksonXmlProperty(isAttribute = true)
    private String reference;

    @JacksonXmlProperty
    private String accountNumber;

    @JacksonXmlProperty
    private String description;

    @JacksonXmlProperty
    private String startBalance;

    @JacksonXmlProperty
    private String mutation;

    @JacksonXmlProperty
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

}
