package com.brandbank.transactions.application.controller;

import com.brandbank.transactions.application.response.LoginResponse;
import com.brandbank.transactions.domain.model.entity.UserAuthenticated;
import com.brandbank.transactions.domain.model.request.UserLogin;
import com.brandbank.transactions.infra.sercurity.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (UserLogin login){
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.accountCode(),login.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserAuthenticated) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
