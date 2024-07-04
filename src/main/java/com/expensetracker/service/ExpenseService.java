package com.expensetracker.service;

import com.expensetracker.dto.ExpenseDto;
import com.expensetracker.model.Expense;

import java.util.List;

public interface ExpenseService {
    Expense addExpense(ExpenseDto expenseDto);
    List<ExpenseDto> getAllExpenses();
    List<Expense> getExpensesByUser(Long userId);
    Expense getExpenseById(Long id);
    void deleteExpenseById(Long id);
    // Add other methods as needed
}
