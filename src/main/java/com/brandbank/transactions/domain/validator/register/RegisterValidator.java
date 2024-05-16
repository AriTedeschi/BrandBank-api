package com.brandbank.transactions.domain.validator.register;

import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.validator.register.rule.AddressValidator;
import com.brandbank.transactions.domain.validator.register.rule.AgeValidator;
import com.brandbank.transactions.domain.validator.register.rule.EmailValidator;
import com.brandbank.transactions.domain.validator.register.rule.NameValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

public class RegisterValidator implements ValidationProcess {
    private Map<String, String> errors = new HashMap<>();
    private Validator<String> initValidator;

    public RegisterValidator(UserRequest request){
        this.initValidator = new EmailValidator(request.email(),this,
                             new NameValidator(request.name(), this,
                             new AgeValidator(request.age(), this,
                             new AddressValidator(request.address(), this, null))));
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
