package com.thomas.trackingapp.domain.finance;

import com.thomas.trackingapp.domain.finance.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/finance-transactions")
@RequiredArgsConstructor
public class FinanceTransactionController {

    private final FinanceTransactionService financeTransactionService;

    private static final Long TEMP_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<FinanceTransactionResponse> createTransaction(
            @RequestBody @Valid FinanceTransactionRequest request) {
        FinanceTransactionResponse response = financeTransactionService.createTransaction(TEMP_USER_ID, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FinanceTransactionResponse>> getTransactionsByRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        List<FinanceTransactionResponse> response =
                financeTransactionService.getTransactionsByRange(TEMP_USER_ID, from, to);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceTransactionResponse> updateTransaction(
            @PathVariable Long id,
            @RequestBody @Valid FinanceTransactionRequest request) {
        FinanceTransactionResponse response =
                financeTransactionService.updateTransaction(TEMP_USER_ID, id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        financeTransactionService.deleteTransaction(TEMP_USER_ID, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<FinanceMonthlySummary> getMonthlySummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        FinanceMonthlySummary response =
                financeTransactionService.getMonthlySummary(TEMP_USER_ID, from, to);
        return ResponseEntity.ok(response);
    }
}
