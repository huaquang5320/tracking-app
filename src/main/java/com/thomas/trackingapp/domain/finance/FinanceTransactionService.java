package com.thomas.trackingapp.domain.finance;

import com.thomas.trackingapp.common.exception.ResourceNotFoundException;
import com.thomas.trackingapp.domain.finance.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceTransactionService {

    private final FinanceTransactionRepository financeTransactionRepository;

    public FinanceTransactionResponse createTransaction(Long userId, FinanceTransactionRequest request) {
        FinanceTransaction transaction = new FinanceTransaction();
        transaction.setUserId(userId);
        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());
        transaction.setCategory(request.getCategory());
        transaction.setDescription(request.getDescription());
        transaction.setLoggedDate(request.getLoggedDate());

        FinanceTransaction saved = financeTransactionRepository.save(transaction);
        return FinanceTransactionResponse.from(saved);
    }

    public List<FinanceTransactionResponse> getTransactionsByRange(Long userId, LocalDate from, LocalDate to) {
        return financeTransactionRepository
                .findByUserIdAndLoggedDateBetweenOrderByLoggedDateDesc(userId, from, to)
                .stream()
                .map(FinanceTransactionResponse::from)
                .collect(Collectors.toList());
    }

    public FinanceTransactionResponse updateTransaction(Long userId, Long id, FinanceTransactionRequest request) {
        FinanceTransaction transaction = financeTransactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());
        transaction.setCategory(request.getCategory());
        transaction.setDescription(request.getDescription());

        FinanceTransaction saved = financeTransactionRepository.save(transaction);
        return FinanceTransactionResponse.from(saved);
    }

    public void deleteTransaction(Long userId, Long id) {
        FinanceTransaction transaction = financeTransactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        financeTransactionRepository.delete(transaction);
    }

    public FinanceMonthlySummary getMonthlySummary(Long userId, LocalDate from, LocalDate to) {
        List<FinanceTransaction> transactions = financeTransactionRepository
                .findByUserIdAndLoggedDateBetweenOrderByLoggedDateDesc(userId, from, to);

        BigDecimal totalIncome = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .map(FinanceTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .map(FinanceTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, BigDecimal> expenseByCategory = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        FinanceTransaction::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, FinanceTransaction::getAmount, BigDecimal::add)
                ));

        return new FinanceMonthlySummary(totalIncome, totalExpense, totalIncome.subtract(totalExpense), expenseByCategory);
    }
}
