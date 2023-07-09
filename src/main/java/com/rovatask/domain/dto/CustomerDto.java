package com.rovatask.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rovatask.domain.Transaction;

import java.util.List;

@JsonDeserialize
public class CustomerDto {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("transactionsList")
    private List<Transaction> transactions;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
