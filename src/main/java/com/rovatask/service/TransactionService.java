package com.rovatask.service;

import com.rovatask.domain.dto.CustomerDto;

public interface TransactionService {

    void createNewCurrentAccount(Integer customerId);

    CustomerDto findCustomerAccount(Integer customerId);
}
