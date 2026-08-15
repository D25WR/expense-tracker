package com.divya.expensetracker.service;

import com.divya.expensetracker.dto.ExpenseDtos.*;
import com.divya.expensetracker.entity.Category;
import com.divya.expensetracker.entity.Expense;
import com.divya.expensetracker.entity.User;
import com.divya.expensetracker.exception.ResourceNotFoundException;
import com.divya.expensetracker.repository.CategoryRepository;
import com.divya.expensetracker.repository.ExpenseRepository;
import com.divya.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ExpenseResponse addExpense(String userEmail, ExpenseRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        }

        Expense expense = Expense.builder()
                .amount(request.getAmount())
                .note(request.getNote())
                .date(request.getDate())
                .category(category)
                .user(user)
                .build();

        Expense saved = expenseRepository.save(expense);
        return toResponse(saved);
    }

    public List<ExpenseResponse> getExpensesForUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return expenseRepository.findByUserIdOrderByDateDesc(user.getId())
                .stream().map(this::toResponse).toList();
    }

    public void deleteExpense(String userEmail, Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        if (!expense.getUser().getEmail().equals(userEmail)) {
            throw new IllegalArgumentException("You cannot delete another user's expense");
        }
        expenseRepository.delete(expense);
    }

    private ExpenseResponse toResponse(Expense e) {
        return new ExpenseResponse(
                e.getId(), e.getAmount(), e.getNote(), e.getDate(),
                e.getCategory() != null ? e.getCategory().getName() : "Uncategorized"
        );
    }
}
