package com.brandbank.transactions.domain.validator.register;

import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.validator.register.rule.EmailValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {

    @ParameterizedTest
    @CsvSource(value={"null","@","@gmail.com", "aaaaaa@gmail", "aaaa-aa@.com"},nullValues={"null"})
    void shouldFail(String email){
        UserRequest request = new UserRequest(email,null,null, null, null);
        RegisterValidator registerValidator = new RegisterValidator(request);
        new EmailValidator(request.email(), registerValidator,null).validate();
        assertTrue(registerValidator.containsError());
    }

    @ParameterizedTest
    @CsvSource({"a@gmail.com","a.a@hotmail.com", "b@yahoo.com", "c@outlook.com"})
    void shouldPass(String email){
        UserRequest request = new UserRequest(email,null,null, null, null);
        RegisterValidator registerValidator = new RegisterValidator(request);
        new EmailValidator(request.email(), registerValidator,null).validate();
        assertFalse(registerValidator.containsError());
    }

}
