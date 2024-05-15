package com.brandbank.transactions.domain.validator.patch;

import com.brandbank.transactions.domain.model.request.UserPatchRequest;
import com.brandbank.transactions.domain.validator.register.PatchValidator;
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
        UserPatchRequest request = new UserPatchRequest(null,age, null);
        PatchValidator registerValidator = new PatchValidator(request);
        new AgeValidator(request.age(), registerValidator,null).disableNullCheck().validate();
        assertTrue(registerValidator.containsError());
    }

    @ParameterizedTest
    @MethodSource("generate")
    void shouldPass(Integer age){
        UserPatchRequest request = new UserPatchRequest(null,age, null);
        PatchValidator registerValidator = new PatchValidator(request);
        new AgeValidator(request.age(), registerValidator,null).disableNullCheck().validate();
        assertFalse(registerValidator.containsError());
    }

    static Stream<Arguments> generateIncorrect() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-1),
                Arguments.of(-99)
        );
    }
    static Stream<Arguments> generate() {
        return Stream.of(
                null,
                Arguments.of(1),
                Arguments.of(10),
                Arguments.of(101)
        );
    }

}
