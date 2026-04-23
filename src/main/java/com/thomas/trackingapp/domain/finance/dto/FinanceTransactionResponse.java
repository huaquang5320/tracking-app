package com.thomas.trackingapp.domain.finance.dto;

import com.thomas.trackingapp.domain.finance.FinanceTransaction;
import com.thomas.trackingapp.domain.finance.TransactionType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class FinanceTransactionResponse {

    private Long id;
    private TransactionType type;
    private BigDecimal amount;
    private String category;
    private String description;
    private LocalDate loggedDate;
    private LocalDateTime createdAt;

    public static FinanceTransactionResponse from(FinanceTransaction entity) {
        return FinanceTransactionResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .amount(entity.getAmount())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .loggedDate(entity.getLoggedDate())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
