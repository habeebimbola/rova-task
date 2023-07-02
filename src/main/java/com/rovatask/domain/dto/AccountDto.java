package com.rovatask.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;

@JsonSerialize
public class AccountDto {

    @JsonProperty("customerID")
    @NotNull
    private Integer customerID;

    @JsonProperty("initialCredit")
    @NotNull
    private Double initialCredit;

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
}
