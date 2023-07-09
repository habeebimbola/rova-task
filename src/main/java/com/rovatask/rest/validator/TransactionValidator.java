package com.rovatask.rest.validator;

import org.springframework.validation.BindingResult;
import java.util.ArrayList;
import java.util.List;

public class TransactionValidator {

    private List<String> errorsList = new ArrayList<>();
    private final BindingResult bindingResult;

    private TransactionValidator(BindingResult bindingResult)
    {
        this.bindingResult = bindingResult;
    }

    public void addErrors(BindingResult bindingResult)
    {
        bindingResult.getAllErrors().stream().forEach((error)-> errorsList.add(error.getDefaultMessage()));
    }

    public List<String> getErrorsList() {
        return errorsList;
    }
}
