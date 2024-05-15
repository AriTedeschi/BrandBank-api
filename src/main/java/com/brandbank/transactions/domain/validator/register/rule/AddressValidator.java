package com.brandbank.transactions.domain.validator.register.rule;

import com.brandbank.transactions.domain.validator.register.ValidationProcess;
import com.brandbank.transactions.domain.validator.register.Validator;

public class AddressValidator extends Validator<String> {

    public AddressValidator(String address, ValidationProcess registerValidator, Validator<String> nextValidation){
        super(address, registerValidator, nextValidation,"address:Provide a address!");
    }

    @Override
    public void validate(){
        super.isNull();
    }
}
