package com.brandbank.transactions.domain.validator.register.rule;

import com.brandbank.transactions.domain.validator.register.Validator;
import com.brandbank.transactions.domain.validator.register.RegisterValidator;

public class AgeValidator extends Validator<Integer> {
    private static final Integer ZERO = 0;
    private Integer age;

    public AgeValidator(Integer age, RegisterValidator registerValidator, Validator<?> nextValidation){
        super(age, registerValidator, nextValidation,"age:Provide an age!");
        this.age=age;
    }

    @Override
    public void validate(){
        super.validate();
        if(super.isNull())
            return;

        if(age.compareTo(ZERO) <= 0)
            registerValidator.addError("age","Age error, please provide a correct age");
    }
}
