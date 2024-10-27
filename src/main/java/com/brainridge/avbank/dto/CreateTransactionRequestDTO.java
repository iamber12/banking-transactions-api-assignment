package com.brainridge.avbank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateTransactionRequestDTO {
    @NotBlank(message = "Payer email is required.")
    @Email(message="Please enter a valid email address.")
    private String payerEmail;

    @NotBlank(message = "Payee email is required.")
    @Email(message="Please enter a valid email address.")
    private String payeeEmail;

    @NotNull(message = "Amount is required.")
    @DecimalMin(value = "0", inclusive = false, message = "Please enter a valid amount.")
    private BigDecimal amount;
}
