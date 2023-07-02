package com.rovatask.service;

import com.rovatask.domain.CustomerAccount;
import com.rovatask.domain.dto.CustomerDto;
import com.rovatask.repo.CustomerAccountRepository;
import com.rovatask.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImp implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final CustomerAccountRepository customerAccountRepository;

    public TransactionServiceImp(TransactionRepository transactionRepository, CustomerAccountRepository customerAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.customerAccountRepository = customerAccountRepository;
    }

    @Override
    public void createNewCurrentAccount(Integer customerId) {

        if(customerId == null)
            throw new RuntimeException("Customer ID Cannot Be Null.");

        this.transactionRepository.save(null);

    }

    @Override
    public CustomerDto findCustomerAccount(Integer customerId) {
        Optional<CustomerAccount> customerAccountOptional = this.customerAccountRepository.findByCustomerId(customerId);

        if(!customerAccountOptional.isPresent())
        {
            return new CustomerDto();
        }
        CustomerAccount customerAccount = customerAccountOptional.get();
        CustomerDto customerDto = new CustomerDto();

        customerDto.setFirstName(customerAccount.getFirstName());
        customerDto.setBalance(customerAccount.getBalance());
        customerDto.setSurname(customerAccount.getSurname());
        customerDto.setTransactions(List.copyOf( customerAccount.getAccountTransactions()));

        return customerDto;
    }


}
