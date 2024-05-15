package com.brandbank.transactions.domain.validator.register.rule;

import com.brandbank.transactions.domain.validator.register.Validator;
import com.brandbank.transactions.domain.validator.register.RegisterValidator;

public class AddressValidator extends Validator<String> {

    public AddressValidator(String address, RegisterValidator registerValidator, Validator<String> nextValidation){
        super(address, registerValidator, nextValidation,"address:Provide a address!");
    }

    @Override
    public void validate(){
        super.isNull();
    }
}
