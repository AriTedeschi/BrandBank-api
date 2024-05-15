package com.brandbank.transactions.domain.validator.register;

import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.validator.register.rule.NameValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameValidatorTest {

    @ParameterizedTest
    @CsvSource(value={"null","_Arthur","Vitor#Ribeiro", "José@"},nullValues={"null"})
    void shouldFail(String name){
        UserRequest request = new UserRequest(null,name,null, null);
        RegisterValidator registerValidator = new RegisterValidator(request);
        new NameValidator(request.name(), registerValidator,null).validate();
        assertTrue(registerValidator.containsError());
    }

    @ParameterizedTest
    @CsvSource({"Ari","Rafael Justo", "ViNiCIUS"})
    void shouldPass(String name){
        UserRequest request = new UserRequest(null,name,null, null);
        RegisterValidator registerValidator = new RegisterValidator(request);
        new NameValidator(request.name(), registerValidator,null).validate();
        assertFalse(registerValidator.containsError());
    }

}
