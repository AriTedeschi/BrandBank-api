package com.brandbank.transactions.domain.validator.register;

import com.brandbank.transactions.domain.model.request.UserPatchRequest;
import com.brandbank.transactions.domain.validator.register.rule.AddressValidator;
import com.brandbank.transactions.domain.validator.register.rule.AgeValidator;
import com.brandbank.transactions.domain.validator.register.rule.NameValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

public class PatchValidator implements ValidationProcess {
    private Map<String, String> errors = new HashMap<>();
    private Validator<String> initValidator;

    public PatchValidator(UserPatchRequest request){
        this.initValidator = new NameValidator(request.name(), this,
                             new AgeValidator(request.age(), this,
                             new AddressValidator(request.address(), this, null).disableNullCheck()).disableNullCheck()).disableNullCheck();
    }

    public void addError(String field, String error){
        this.errors.put(field,error);
    }

    public boolean containsError(){return !this.errors.isEmpty();}

    public void validate() {
        this.initValidator.validate();
        if(containsError()){
            StringBuilder errorMessage = new StringBuilder("Errors occurred in the following fields:\n");
            errors.forEach((field, msg) ->
                errorMessage.append(field).append(": ").append(msg).append("\n")
            );
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }
    }
}
