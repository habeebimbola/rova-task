package com.rovatask.service;

import com.rovatask.domain.Account;
import com.rovatask.domain.AccountType;
import com.rovatask.domain.Customer;
import com.rovatask.domain.Transaction;
import com.rovatask.domain.dto.CustomerDto;
import com.rovatask.domain.dto.TransactionDto;
import com.rovatask.repo.CustomerRepository;
import com.rovatask.repo.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TransactionServiceImp implements TransactionService{

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImp.class);
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public TransactionServiceImp(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto findCustomerAccount(Integer customerId) {

        Optional<Customer> customerAccountOptional = this.customerRepository.findByCustomerId(customerId);

        if(!customerAccountOptional.isPresent())
        {
            return new CustomerDto();
        }
        Customer customer = customerAccountOptional.get();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setBalance(customer.getCustomerAccount().getInitialBalance());
        customerDto.setSurname(customer.getSurname());
        customerDto.setTransactions(Optional.ofNullable( customer.getCustomerAccount().getTransactionList()).orElse(new ArrayList<Transaction>()));

        return customerDto;
    }

    @Override
    public Transaction createNewTransaction(TransactionDto transactionDto) {

        if( transactionDto == null)
        {
            throw new  RuntimeException("Transaction Cannot Be Null");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        transaction.setTransactionAmount(transactionDto.getAmount());
        transaction.setAccountId(transactionDto.setAccountId);

        Transaction newTransaction = this.transactionRepository.save(transaction);

        return newTransaction;

    }


}
