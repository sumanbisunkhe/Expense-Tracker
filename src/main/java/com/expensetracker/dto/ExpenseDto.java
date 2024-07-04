package com.expensetracker.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseDto {

    private Long id;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Date is required")
    private LocalDateTime date;

    private LocalDateTime timestamp;

    @NotNull(message = "User ID is required")
    private Long userId;

    // Constructors

    public ExpenseDto() {
        this.timestamp = LocalDateTime.now();
    }

    public ExpenseDto(Long id, String description, BigDecimal amount, @NotNull LocalDateTime date, Long userId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.userId = userId;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public @NotNull(message = "Date is required") LocalDateTime getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
