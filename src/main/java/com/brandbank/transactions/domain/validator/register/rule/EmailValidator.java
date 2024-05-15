package com.brandbank.transactions.domain.validator.register.rule;

import com.brandbank.transactions.domain.validator.register.Validator;
import com.brandbank.transactions.domain.validator.register.RegisterValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends Validator<String> {
    private static final String EMAIL_REGEX = "^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";
    private String email;

    public EmailValidator(String email, RegisterValidator registerValidator, Validator<String> nextValidation){
        super(email,registerValidator, nextValidation,"email:Provide an email!");
        this.email=email;
    }

    @Override
    public void validate(){
        super.validate();
        if(super.isNull())
            return;

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches())
            registerValidator.addError("email","Email syntax error, please provide a correct email");
    }
}
