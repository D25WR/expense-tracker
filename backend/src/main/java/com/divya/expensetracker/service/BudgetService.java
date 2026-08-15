package com.divya.expensetracker.service;

import com.divya.expensetracker.dto.BudgetSummaryResponse;
import com.divya.expensetracker.entity.Budget;
import com.divya.expensetracker.entity.User;
import com.divya.expensetracker.repository.BudgetRepository;
import com.divya.expensetracker.repository.ExpenseRepository;
import com.divya.expensetracker.repository.UserRepository;
import com.divya.expensetracker.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    /** Compares each category's budget for the given month/year against actual spend. */
    public List<BudgetSummaryResponse> getMonthlySummary(String userEmail, int month, int year) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Budget> budgets = budgetRepository.findByUserIdAndMonthAndYear(user.getId(), month, year);

        Map<String, BigDecimal> spentByCategory = expenseRepository
                .sumByCategoryForMonth(user.getId(), month, year).stream()
                .collect(Collectors.toMap(row -> (String) row[0], row -> (BigDecimal) row[1]));

        return budgets.stream().map(b -> {
            String categoryName = b.getCategory() != null ? b.getCategory().getName() : "Uncategorized";
            BigDecimal spent = spentByCategory.getOrDefault(categoryName, BigDecimal.ZERO);
            BigDecimal remaining = b.getLimitAmount().subtract(spent);
            return new BudgetSummaryResponse(categoryName, b.getLimitAmount(), spent, remaining,
                    remaining.compareTo(BigDecimal.ZERO) < 0);
        }).toList();
    }
}
