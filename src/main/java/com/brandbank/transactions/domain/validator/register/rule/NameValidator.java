package com.brandbank.transactions.domain.validator.register.rule;

import com.brandbank.transactions.domain.validator.register.ValidationProcess;
import com.brandbank.transactions.domain.validator.register.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator extends Validator<String> {
    private static final String NAME_REGEX = "^[a-zA-Z0-9\\s]*$";
    private String name;

    public NameValidator(String name, ValidationProcess registerValidator, Validator<Integer> nextValidation){
        super(name, registerValidator, nextValidation,"name:Provide a name!");
        this.name=name;
    }

    @Override
    public void validate(){
        super.validate();
        if(super.isNull())
            return;

        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        if(!matcher.matches())
            registerValidator.addError("name","Name syntax error, please provide a correct name");
    }
}
