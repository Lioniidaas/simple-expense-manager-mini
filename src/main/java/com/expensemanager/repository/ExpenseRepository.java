package com.expensemanager.repository;

import com.expensemanager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // You can add custom query methods if needed, but JpaRepository provides basic CRUD operations.
}
