package com.thomas.trackingapp.domain.finance.dto;

import com.thomas.trackingapp.domain.finance.TransactionType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class FinanceTransactionRequest {

    @NotNull(message = "Type is required")
    private TransactionType type;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "Category is required")
    private String category;

    private String description;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate loggedDate;
}
