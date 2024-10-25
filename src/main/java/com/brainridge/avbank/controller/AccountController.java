package com.brainridge.avbank.controller;

import com.brainridge.avbank.constants.ApiUrlConstants;
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
@RequestMapping(ApiUrlConstants.BASE_URL + "/accounts")
@Validated
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<CreateAccountResponseDTO> createAccount(@Valid @RequestBody CreateAccountRequestDTO createAccountRequestDTO) {
        CreateAccountResponseDTO newAccount = accountService.createAccount(createAccountRequestDTO);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<CreateAccountResponseDTO> getAccount(@PathVariable Long accountId) throws AccountNotFoundException {
        CreateAccountResponseDTO account = accountService.findById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
