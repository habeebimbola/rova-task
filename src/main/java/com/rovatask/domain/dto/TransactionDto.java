package com.rovatask.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionDto {

    public Integer setAccountId;
    private LocalDate transactionDate;
    private Integer transactionId;
    private Integer transactionReference;
    private Double amount;

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(Integer transactionReference) {
        this.transactionReference = transactionReference;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAccountId(Integer setAccountId) {
        this.setAccountId = setAccountId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
