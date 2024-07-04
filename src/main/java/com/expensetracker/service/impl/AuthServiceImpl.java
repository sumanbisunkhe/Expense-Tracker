package com.expensetracker.service.impl;

import com.expensetracker.enums.Role;
import com.expensetracker.model.User;
import com.expensetracker.service.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public boolean isAdmin() {
        return getCurrentUser().getRoles().contains(Role.ADMIN);
    }
}
