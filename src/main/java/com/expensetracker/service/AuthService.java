package com.expensetracker.service;

import com.expensetracker.model.User;

public interface AuthService {

    User getCurrentUser();

    boolean isAdmin();
}
