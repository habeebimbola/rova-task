package com.rovatask.service;

import com.rovatask.domain.dto.CustomerDto;

public interface TransactionService {

    CustomerDto createNewCurrentAccount(Integer customerId, Double initialBalance);

    CustomerDto findCustomerAccount(Integer customerId);
}
