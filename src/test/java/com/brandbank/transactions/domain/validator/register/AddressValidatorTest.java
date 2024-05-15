package com.brandbank.transactions.domain.validator.register;

import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.validator.register.rule.AddressValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddressValidatorTest {

    @ParameterizedTest
    @CsvSource(value={"null"},nullValues={"null"})
    void shouldFail(String address){
        UserRequest request = new UserRequest(null,null,null, address);
        RegisterValidator registerValidator = new RegisterValidator(request);
        new AddressValidator(request.address(), registerValidator,null).validate();
        assertTrue(registerValidator.containsError());
    }

    @ParameterizedTest
    @CsvSource({"Rua Jatobá 345","Rua ### Velheiros"})
    void shouldPass(String address){
        UserRequest request = new UserRequest(null,null,null, address);
        RegisterValidator registerValidator = new RegisterValidator(request);
        new AddressValidator(request.address(), registerValidator,null).validate();
        assertFalse(registerValidator.containsError());
    }

}
