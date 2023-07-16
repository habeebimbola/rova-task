package com.rovatask.rest;

import com.rovatask.domain.Account;
import com.rovatask.domain.dto.AccountDto;
import com.rovatask.domain.dto.CustomerDto;
import com.rovatask.rest.validator.TransactionValidatorBuilder;
import com.rovatask.service.AccountService;
import com.rovatask.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;

    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createNewCurrentAccount(@RequestBody @Valid AccountDto accountDto , BindingResult bindingResult)
    {

        if(bindingResult.hasErrors())
        {
            return ResponseEntity.badRequest().body(TransactionValidatorBuilder.fromBindingResult(bindingResult));
        }

        AccountDto savedAccountDto = this.accountService.createNewCurrentAccount(accountDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{fullName}"). buildAndExpand(savedAccountDto.getAccountName()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/customer-detail/{custId}")
    public ResponseEntity<?> viewCustomerDetail(@PathVariable("custId") Integer customerId)
    {

        if(customerId == null)
        {
            return ResponseEntity.badRequest().body("Invalid Customer ID Parameter");
        }

        CustomerDto customerDto = this.transactionService.findCustomerAccount(customerId);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/get-account/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("accountId") Integer accountId)
    {
        Optional<AccountDto> accountDtoOptional  = this.accountService.findAccountById(accountId);

        if (!accountDtoOptional.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AccountDto());
        }
        return ResponseEntity.of(accountDtoOptional);
    }
}
