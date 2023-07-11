package com.rovatask.rest;

import com.rovatask.domain.dto.AccountDto;
import com.rovatask.domain.dto.CustomerDto;
import com.rovatask.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> createNewCurrentAccount(@RequestBody @Valid AccountDto accountDto , BindingResult bindingResult)
    {

        if(bindingResult.hasErrors())
        {
            return ResponseEntity.badRequest().body("");
        }

        CustomerDto customerDto = this.transactionService.createNewCurrentAccount(accountDto.getCustomerID(), accountDto.getInitialCredit());


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand("{fullname}",customerDto.getSurname().concat(customerDto.getFirstName())).toUri();
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build("{fullname}","Habeeb Animashaun");
        return ResponseEntity.created(uri).header("Location",uri.toString()).build();
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
}
