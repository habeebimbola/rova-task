package com.rovatask.service;

import com.rovatask.domain.Account;
import com.rovatask.domain.AccountType;
import com.rovatask.domain.Customer;
import com.rovatask.domain.dto.AccountDto;
import com.rovatask.domain.dto.TransactionDto;
import com.rovatask.repo.AccountRepository;
import com.rovatask.repo.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    @Qualifier("transactionServiceImp")
    private TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
       this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public AccountDto createNewCurrentAccount(AccountDto accountDto) {

        if(accountDto.getCustomerID() == null || accountDto.getInitialCredit() == null)
        {
            throw new RuntimeException("Customer ID Cannot Be Null.");
        }

         Optional<Customer> customerOptional = customerRepository.findByCustomerId(accountDto.getCustomerID());

        if(!customerOptional.isPresent())
        {
            throw new RuntimeException("Customer ID Does Not Exist.");
        }

        Account account = new Account();
        account.setAccountType(AccountType.CURRENT_ACCOUNT);
        account.setAccountOwner(customerOptional.get());
        account.setInitialBalance(accountDto.getInitialCredit());
        account.setAccountNumber(customerOptional.get().getCustomerAccount().getAccountNumber());

        Account newAccount = this.accountRepository.save(account);

        if(accountDto.getInitialCredit() > 0D)
        {
            addNewTransaction(account,accountDto.getInitialCredit());
        }

        AccountDto newAccountDto = new AccountDto();
        newAccountDto.setAccountType(newAccount.getAccountType());
        newAccountDto.setCustomerID(newAccount.getAccountOwner().getCustomerId());
        newAccountDto.setInitialCredit(newAccount.getInitialBalance());
        newAccountDto.setAccountName(newAccount.getAccountOwner().getFirstName()+"-"+newAccount.getAccountOwner().getSurname());


        return  newAccountDto;
    }

    @Override
    public Optional<AccountDto> findAccountById(Integer accountNumber) {

        Optional<Account> accountOptional = this.accountRepository.findByAccountNumber(accountNumber);
        if (!accountOptional.isPresent())
        {
            throw new RuntimeException("Account Not Found");
        }

         Account account = accountOptional.get();
         AccountDto accountDto = new AccountDto();
         accountDto.setAccountName(account.getAccountOwner().getSurname());
         accountDto.setAccountType(account.getAccountType());
         accountDto.setCustomerID(account.getAccountOwner().getCustomerId());
         LOGGER.info("Transactions: "+account.getTransactionList().size());
         accountDto.setTransactions(account.getTransactionList());

        return Optional.of(accountDto);
    }

    private void addNewTransaction(Account account, Double initialCredit) {

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountId(account.getId());
        transactionDto.setTransactionDate(LocalDate.now());
        transactionDto.setAmount(initialCredit);

        transactionService.createNewTransaction(transactionDto);

    }
}
