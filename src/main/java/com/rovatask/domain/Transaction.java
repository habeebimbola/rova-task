package com.rovatask.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @Column(name = "ID")
    private Integer id;

    @CreatedDate()
    @Column(name = "CREATED_DATE")
    @Temporal(value = TemporalType.DATE)
    private LocalDate transactionDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @PrePersist
    public void OnCreate()
    {
        this.setTransactionDate(LocalDateTime.now().toLocalDate());
    }
}