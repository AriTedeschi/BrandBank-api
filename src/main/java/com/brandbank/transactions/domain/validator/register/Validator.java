package com.brandbank.transactions.domain.validator.register;

import java.util.Objects;

public abstract class Validator<T> {
    protected RegisterValidator registerValidator;
    private Validator<?> nextValidation;
    private String nullMsg;

    protected T field;

    protected Validator(T field, RegisterValidator registerValidator, Validator<?> nextValidation, String nullMsg){
        this.field = field;
        this.registerValidator=registerValidator;
        this.nextValidation=nextValidation;
        this.nullMsg=nullMsg;
    }

    public void validate() {
        validateNext();
    }

    protected boolean isNull() {
        String errorField = nullMsg.split(":")[0];
        String msg = nullMsg.split(":")[1];
        if(Objects.isNull(this.field))
            registerValidator.addError(errorField,msg);
        return Objects.isNull(this.field);
    }
    private void validateNext(){
        if(nextValidation != null)
            nextValidation.validate();
    }
}
