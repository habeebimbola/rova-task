package com.rovatask.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity()
@Table(name = "CUSTOMER_ACCOUNT", uniqueConstraints = @UniqueConstraint(columnNames = "CUSTOMER_ID"))
public class CustomerAccount implements Serializable {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CUSTOMER_ID", nullable = false, unique = true)
    private Integer customerId;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @Column(name = "DATE_MODIFIED_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

    @Transient
//    @OneToMany( )
    private Set<Transaction> accountTransactions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

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

    public Set<Transaction> getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(Set<Transaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @PreUpdate
    public void updateLastModifiedDate()
    {
        this.setLastModifiedDate(LocalDateTime.now());
    }
}
