package com.expensetracker.controller;

import com.expensetracker.dto.ExpenseDto;
import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Object> addExpense(@PathVariable Long userId, @RequestBody ExpenseDto expenseDto) {
        expenseDto.setUserId(userId); // Set userId from path variable
        Expense addedExpense = expenseService.addExpense(expenseDto);
        if (addedExpense != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Expense, '" + addedExpense.getDescription() + ", added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to add expense.");
        }
    }


    @GetMapping("/all")
    public ResponseEntity<Object> getAllExpenses() {
        List<ExpenseDto> expenseDtos = expenseService.getAllExpenses();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Expense list fetched successfully.");
        response.put("expenses", expenseDtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Expense found.");
            response.put("expense", mapExpenseToDto(expense));
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Expense not found for id: " + id);
        }
    }

    private ExpenseDto mapExpenseToDto(Expense expense) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        dto.setUserId(expense.getUser().getId()); // Assuming you want to include user ID only
        dto.setTimestamp(LocalDateTime.now()); // Optional: Include timestamp if needed
        return dto;
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getExpensesByUser(@PathVariable Long userId) {
        List<Expense> expenses = expenseService.getExpensesByUser(userId);
        if (!expenses.isEmpty()) {
            List<ExpenseDto> expenseDtos = expenses.stream()
                    .map(this::mapExpenseToDto)
                    .collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Expenses found for user id: " + userId);
            response.put("expenses", expenseDtos);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No expenses found for user id: " + userId);
        }
    }


    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Object> deleteExpenseById(@PathVariable Long id) {
        Expense expenseToDelete = expenseService.getExpenseById(id);
        if (expenseToDelete != null) {
            expenseService.deleteExpenseById(id);
            return ResponseEntity.ok("Expense deleted successfully for id: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Expense not found for id: " + id);
        }
    }
}