package com.rovatask.rest.validator;

import org.springframework.validation.BindingResult;

public class TransactionValidatorBuilder {

    public static TranasctionValidator fromBindingResult(BindingResult bindingResult)
    {
        TranasctionValidator tranasctionValidator = new TranasctionValidator("There are "+bindingResult.getErrorCount()+" total errors. Please review.");
        bindingResult.getAllErrors().stream().forEach((error)->{ tranasctionValidator.addErrors(error.getDefaultMessage());});
        return tranasctionValidator;
    }


}
