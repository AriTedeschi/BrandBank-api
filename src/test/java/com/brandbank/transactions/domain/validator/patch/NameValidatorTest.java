package com.brandbank.transactions.domain.validator.patch;

import com.brandbank.transactions.domain.model.request.UserPatchRequest;
import com.brandbank.transactions.domain.validator.register.PatchValidator;
import com.brandbank.transactions.domain.validator.register.rule.NameValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameValidatorTest {

    @ParameterizedTest
    @CsvSource(value={"_Arthur","Vitor#Ribeiro", "José@"})
    void shouldFail(String name){
        UserPatchRequest request = new UserPatchRequest(name,null, null);
        PatchValidator registerValidator = new PatchValidator(request);
        new NameValidator(request.name(), registerValidator,null).disableNullCheck().validate();
        assertTrue(registerValidator.containsError());
    }

    @ParameterizedTest
    @CsvSource(value = {"null","Ari","Rafael Justo", "ViNiCIUS"},nullValues={"null"})
    void shouldPass(String name){
        UserPatchRequest request = new UserPatchRequest(name,null, null);
        PatchValidator registerValidator = new PatchValidator(request);
        new NameValidator(request.name(), registerValidator,null).disableNullCheck().validate();
        assertFalse(registerValidator.containsError());
    }

}
