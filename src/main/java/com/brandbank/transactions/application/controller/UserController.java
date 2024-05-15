package com.brandbank.transactions.application.controller;

import com.brandbank.transactions.domain.model.request.UserRequest;
import com.brandbank.transactions.domain.model.response.UserResponse;
import com.brandbank.transactions.domain.service.UserService;
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

    @PostMapping()
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest, HttpServletRequest request){
        UserResponse response = service.register(userRequest);
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{accountCode}")
                .buildAndExpand(response.accountCode())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{accountCode}")
    public ResponseEntity<UserResponse> register(@PathVariable String accountCode){
        UserResponse response = service.getUser(accountCode);
        return ResponseEntity.ok(response);
    }
}
