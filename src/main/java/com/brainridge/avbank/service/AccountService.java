package com.brainridge.avbank.service;

import com.brainridge.avbank.dto.CreateAccountRequestDTO;
import com.brainridge.avbank.dto.CreateAccountResponseDTO;

public interface AccountService {
    CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO);
    CreateAccountResponseDTO findById(Long accountId);
}
