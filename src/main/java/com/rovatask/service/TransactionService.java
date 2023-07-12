package com.rovatask.service;

import com.rovatask.domain.Transaction;
import com.rovatask.domain.dto.CustomerDto;
import com.rovatask.domain.dto.TransactionDto;

public interface TransactionService {

    CustomerDto findCustomerAccount(Integer customerId);

    Transaction createNewTransaction(TransactionDto TransactionDto);
}
