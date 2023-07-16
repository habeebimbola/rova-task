package com.rovatask.rest.validator;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class TranasctionValidator {

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<String> errorsList = new ArrayList<>();
    private final String errorMessage;

    public TranasctionValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addErrors(String errorMessage)
    {
        errorsList.add(errorMessage);
    }

    public List<String> getErrorsList() {
        return errorsList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
