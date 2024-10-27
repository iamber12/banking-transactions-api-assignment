package com.brainridge.avbank.controller;

import com.brainridge.avbank.constants.ApiEndpointsConstants;
import com.brainridge.avbank.dto.CreateAccountRequestDTO;
import com.brainridge.avbank.dto.CreateAccountResponseDTO;
import com.brainridge.avbank.exception.AccountNotFoundException;
import com.brainridge.avbank.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpointsConstants.ACCOUNT_BASE_URL)
@Validated
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<CreateAccountResponseDTO> createAccount(@Valid @RequestBody CreateAccountRequestDTO accountRequest) {
        CreateAccountResponseDTO accountResponse = accountService.createAccount(accountRequest);
        return new ResponseEntity<>(accountResponse , HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<CreateAccountResponseDTO> getAccountById(@PathVariable Long accountId) throws AccountNotFoundException {
        CreateAccountResponseDTO accountResponse = accountService.findById(accountId);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }
}
