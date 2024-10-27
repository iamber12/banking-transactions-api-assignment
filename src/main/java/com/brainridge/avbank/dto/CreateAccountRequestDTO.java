package com.brainridge.avbank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CreateAccountRequestDTO {
    @NotBlank(message="Owner name is required.")
    private String ownerName;

    @NotNull(message="Balance is required.")
    @DecimalMin(value="0.0", inclusive=false, message="Balance must be greater than zero.")
    private BigDecimal balance;

    @NotBlank(message="Email address must be populated.")
    @Email(message="Please enter a valid email address.")
    private String email;
}
