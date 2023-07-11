package com.rovatask.service;

import com.rovatask.domain.Account;
import com.rovatask.domain.AccountType;
import com.rovatask.domain.Customer;
import com.rovatask.domain.Transaction;
import com.rovatask.domain.dto.CustomerDto;
import com.rovatask.repo.CustomerRepository;
import com.rovatask.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImp implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public TransactionServiceImp(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto createNewCurrentAccount(Integer customerId, Double initialBalance) {

        if(customerId == null)
            throw new RuntimeException("Customer ID Cannot Be Null.");

        Optional<Customer> customerOptional = this.customerRepository.findByCustomerId(customerId);
        Optional<CustomerDto> customerDto = Optional.ofNullable(new CustomerDto());

        if(customerOptional.isPresent())
        {
            Customer customer = customerOptional.get();
            Account account = customer.getCustomerAccount();
            account.setAccountType(AccountType.CURRENT_ACCOUNT);


            if (initialBalance > 0)
            {
                Transaction transaction = new Transaction();
                transaction.setTransactionAmount(initialBalance);
                account.addTransaction(transaction);
            }

            customer.setCustomerAccount(account);

            Customer updatedCustomer =  this.customerRepository.save(customer);
//            customerDto.map((dto) -> dto.setSurname(updatedCustomer.getSurname()) );
//            customerDto.setFirstName(updatedCustomer.getFirstName());

            return  customerDto.get();

        }
        CustomerDto emptyDto=new CustomerDto();
        emptyDto.setFirstName("");emptyDto.setSurname("");emptyDto.setBalance(0D);
        return  customerDto.orElse( emptyDto);


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
        customerDto.setTransactions(customer.getCustomerAccount().getTransactionList());

        return customerDto;
    }


}
