package com.divya.expensetracker.controller;

import com.divya.expensetracker.dto.BudgetSummaryResponse;
import com.divya.expensetracker.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("/summary")
    public ResponseEntity<List<BudgetSummaryResponse>> getSummary(
            Authentication auth,
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(budgetService.getMonthlySummary(auth.getName(), month, year));
    }
}
