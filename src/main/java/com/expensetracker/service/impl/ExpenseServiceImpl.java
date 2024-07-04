package com.expensetracker.service.impl;

import com.expensetracker.dto.ExpenseDto;
import com.expensetracker.model.Expense;
import com.expensetracker.model.User;
import com.expensetracker.repo.ExpenseRepository;
import com.expensetracker.repo.UserRepository;
import com.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Expense addExpense(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(LocalDateTime.now()); // Set current date and time

        // Fetch the User from the repository
        Optional<User> userOptional = userRepository.findById(expenseDto.getUserId());
        if (userOptional.isPresent()) {
            expense.setUser(userOptional.get());
        } else {
            // Handle the case where the user is not found
            throw new IllegalArgumentException("User not found with id: " + expenseDto.getUserId());
        }

        return expenseRepository.save(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ExpenseDto convertToDto(Expense expense) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        // Set other fields as needed
        return dto;
    }

    @Override
    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        return expenseOptional.orElse(null);
    }

    @Override
    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }
}
