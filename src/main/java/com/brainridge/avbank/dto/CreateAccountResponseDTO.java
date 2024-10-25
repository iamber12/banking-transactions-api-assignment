package com.brainridge.avbank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CreateAccountResponseDTO {
    Long id;
    String ownerName;
    String email;
    BigDecimal balance;
}
