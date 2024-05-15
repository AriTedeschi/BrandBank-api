package com.brandbank.transactions.domain.validator.patch;

import com.brandbank.transactions.domain.model.request.UserPatchRequest;
import com.brandbank.transactions.domain.validator.register.PatchValidator;
import com.brandbank.transactions.domain.validator.register.rule.AddressValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AddressValidatorTest {

    @ParameterizedTest
    @CsvSource(value = {"null","Rua Jatobá 345","Rua ### Velheiros"},nullValues={"null"})
    void shouldPass(String address){
        UserPatchRequest request = new UserPatchRequest(null,null, address);
        PatchValidator registerValidator = new PatchValidator(request);
        new AddressValidator(request.address(), registerValidator,null).disableNullCheck().validate();
        assertFalse(registerValidator.containsError());
    }

}
