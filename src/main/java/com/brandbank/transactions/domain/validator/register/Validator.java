package com.brandbank.transactions.domain.validator.register;

import java.util.Objects;

public abstract class Validator<T> {
    protected ValidationProcess registerValidator;
    private Validator<?> nextValidation;
    private String nullMsg;
    private boolean nullCheck;

    protected T field;

    protected Validator(T field, ValidationProcess registerValidator, Validator<?> nextValidation, String nullMsg){
        this.field = field;
        this.registerValidator=registerValidator;
        this.nextValidation=nextValidation;
        this.nullMsg=nullMsg;
        this.nullCheck = true;
    }

    public void validate() {
        validateNext();
    }

    public Validator<T> disableNullCheck(){
        this.nullCheck=false;
        return this;
    }

    protected boolean isNull() {
        if(!nullCheck)
            return Objects.isNull(this.field);

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
