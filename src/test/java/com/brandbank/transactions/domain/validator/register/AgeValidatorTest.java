package com.brandbank.transactions.domain.validator.register;

import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.validator.register.rule.AgeValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgeValidatorTest {

    @ParameterizedTest
    @MethodSource("generateIncorrect")
    void shouldFail(Integer age){
        UserRequest request = new UserRequest(null,null,age, null);
        RegisterValidator registerValidator = new RegisterValidator(request);
        new AgeValidator(request.age(), registerValidator,null).validate();
        assertTrue(registerValidator.containsError());
    }

    @ParameterizedTest
    @MethodSource("generate")
    void shouldPass(Integer age){
        UserRequest request = new UserRequest(null,null,age, null);
        RegisterValidator registerValidator = new RegisterValidator(request);
        new AgeValidator(request.age(), registerValidator,null).validate();
        assertFalse(registerValidator.containsError());
    }

    static Stream<Arguments> generateIncorrect() {
        return Stream.of(
                null,
                Arguments.of(0),
                Arguments.of(-1),
                Arguments.of(-99)
        );
    }
    static Stream<Arguments> generate() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(10),
                Arguments.of(101)
        );
    }

}
