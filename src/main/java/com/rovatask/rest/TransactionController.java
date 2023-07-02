package com.rovatask.rest;

import com.rovatask.domain.CustomerAccount;
import com.rovatask.domain.dto.AccountDto;
import com.rovatask.domain.dto.CustomerDto;
import com.rovatask.service.TransactionService;
import com.rovatask.service.TransactionServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createNewCurrentAccount(@RequestBody @Valid AccountDto accountDto )
    {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build("{}");
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/customer-detail/{custId}")
    public ResponseEntity<CustomerDto> viewCustomerDetail(@PathVariable("custId") Integer customerId)
    {
        CustomerDto customerDto = this.transactionService.findCustomerAccount(customerId);
        return ResponseEntity.ok(customerDto);
    }
}
