package com.brandbank.transactions.application.controller;

import com.brandbank.transactions.application.response.TransactionResponse;
import com.brandbank.transactions.application.response.UserResponse;
import com.brandbank.transactions.domain.service.TransactionService;
import com.brandbank.transactions.infra.sercurity.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @Operation(summary = "Debt value from authenticated user",
            parameters = { @Parameter(in = ParameterIn.PATH, name = "value", description = "Transactions value", example = "1.25") },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful"),
                    @ApiResponse(responseCode = "400", description = "Invalid value"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public ResponseEntity<UserResponse> debt(@PathVariable String value, HttpServletRequest request){
        String accountCode = retriveAccountCode(request);

        UserResponse response = service.debit(accountCode, new BigDecimal(value));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/credt/{value}")
    @Operation(summary = "Credit value from authenticated user",
            parameters = { @Parameter(in = ParameterIn.PATH, name = "value", description = "Transactions value", example = "1.25") },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful"),
                    @ApiResponse(responseCode = "400", description = "Invalid value"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public ResponseEntity<UserResponse> credt(@PathVariable String value, HttpServletRequest request){
        String accountCode = retriveAccountCode(request);

        UserResponse response = service.credt(accountCode, new BigDecimal(value));
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    @Operation(summary = "Get all users transactions",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public ResponseEntity<Page<TransactionResponse>> listTransactions(
              @RequestParam(value = "page", defaultValue = "0") 		Integer page,
              @RequestParam(value = "linesPerPage", defaultValue = "10")Integer linesPerPage,
              @RequestParam(value = "direction", defaultValue = "DESC") 	String 	direction,
              @RequestParam(value = "orderBy", defaultValue = "created_at") 	String 	orderBy,
              HttpServletRequest request){
        if(page <= -1 || linesPerPage <= 0 || (!direction.equalsIgnoreCase("DESC") && !direction.equalsIgnoreCase("ASC")))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid pagination params!");
        direction = direction.toUpperCase();
        String accountCode = retriveAccountCode(request);

        Pageable pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return ResponseEntity.ok(service.list(accountCode, pageRequest));
    }

    private String retriveAccountCode(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ","");
        String accountCode = tokenService.validateToken(token);
        return accountCode;
    }
}
