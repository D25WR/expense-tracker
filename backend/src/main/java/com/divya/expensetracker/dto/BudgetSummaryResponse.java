package com.divya.expensetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BudgetSummaryResponse {
    private String categoryName;
    private BigDecimal budgeted;
    private BigDecimal spent;
    private BigDecimal remaining;
    private boolean overBudget;
}
