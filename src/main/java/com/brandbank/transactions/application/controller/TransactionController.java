package com.brandbank.transactions.application.controller;

import com.brandbank.transactions.application.response.UserResponse;
import com.brandbank.transactions.domain.service.TransactionService;
import com.brandbank.transactions.infra.sercurity.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(TransactionController.BASE_URL)
public class TransactionController {
    public static final String BASE_URL = "/transactions";
    private final TransactionService service;
    private final TokenService tokenService;

    public TransactionController(TransactionService service, TokenService tokenService) {
        this.service = service;
        this.tokenService = tokenService;
    }

    @PostMapping("/debt/{value}")
    public ResponseEntity<UserResponse> debt(@PathVariable String value, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ","");
        String accountCode = tokenService.validateToken(token);

        UserResponse response = service.debit(accountCode, new BigDecimal(value));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/credt/{value}")
    public ResponseEntity<UserResponse> credt(@PathVariable String value, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ","");
        String accountCode = tokenService.validateToken(token);

        UserResponse response = service.credt(accountCode, new BigDecimal(value));
        return ResponseEntity.ok(response);
    }
}
