package com.brandbank.transactions.application.controller;

import com.brandbank.transactions.application.response.UserResponse;
import com.brandbank.transactions.domain.model.request.UserPatchRequest;
import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
    public static final String BASE_URL = "/users";
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @Operation(summary = "Registrates user",
            responses = {
                @ApiResponse(responseCode = "201", description = "Successful", content = @Content(schema = @Schema(implementation = UserRequest.class))),
                @ApiResponse(responseCode = "400", description = "Field validation wrong field format")
            })
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest, HttpServletRequest request){
        UserResponse response = service.register(userRequest);
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{accountCode}")
                .buildAndExpand(response.accountCode())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{accountCode}")
    @Operation(summary = "Get user by account's code",
            parameters = { @Parameter(in = ParameterIn.PATH, name = "accountCode", description = "User's accountCode") },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public ResponseEntity<UserResponse> getByAccountCode(@PathVariable String accountCode){
        UserResponse response = service.getUser(accountCode);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{accountCode}")
    @Operation(summary = "Patch user",
            parameters = { @Parameter(in = ParameterIn.PATH, name = "accountCode", description = "User's accountCode") },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful", content = @Content(schema = @Schema(implementation = UserPatchRequest.class))),
                    @ApiResponse(responseCode = "400", description = "Field validation wrong field format"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public ResponseEntity<UserResponse> getByAccountCode(@RequestBody UserPatchRequest userRequest, @PathVariable String accountCode, HttpServletRequest request){
        UserResponse response = service.edit(accountCode, userRequest);
        return ResponseEntity.ok(response);
    }
}
