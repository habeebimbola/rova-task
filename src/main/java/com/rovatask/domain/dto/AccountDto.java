package com.rovatask.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rovatask.domain.AccountType;
import com.rovatask.domain.Transaction;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonSerialize
public class AccountDto {

    @JsonProperty("customerID")
    @NotNull
    private Integer customerID;

    @JsonProperty("initialCredit")
    @NotNull
    private Double initialCredit;

    private Integer accountId;

    private AccountType accountType;

    private String accountName;

    private List<Transaction> transactions;

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Double getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(Double initialCredit) {
        this.initialCredit = initialCredit;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setTransactions(List<Transaction> transactionList) {
        this.transactions = transactionList;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
