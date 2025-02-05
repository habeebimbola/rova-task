package com.rovatask.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity()
@Table(name = "CUSTOMER", uniqueConstraints = @UniqueConstraint(columnNames = "CUSTOMER_ID"))
public class Customer implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CUSTOMER_ID", nullable = false, unique = true)
    private Integer customerId;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", referencedColumnName = "ACCOUNT_ID")
    private Account customerAccount;

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

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @PreUpdate
    public void updateLastModifiedDate()
    {
        this.setLastModifiedDate(LocalDateTime.now());
    }

    public Account getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(Account customerAccount) {
        this.customerAccount = customerAccount;
    }
}
