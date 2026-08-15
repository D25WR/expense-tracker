package com.divya.expensetracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDtos {

    @Data
    public static class ExpenseRequest {
        @NotNull @Positive
        private BigDecimal amount;
        private String note;
        @NotNull
        private LocalDate date;
        private Long categoryId;
    }

    @Data
    public static class ExpenseResponse {
        private Long id;
        private BigDecimal amount;
        private String note;
        private LocalDate date;
        private String categoryName;

        public ExpenseResponse(Long id, BigDecimal amount, String note, LocalDate date, String categoryName) {
            this.id = id;
            this.amount = amount;
            this.note = note;
            this.date = date;
            this.categoryName = categoryName;
        }
    }
}
