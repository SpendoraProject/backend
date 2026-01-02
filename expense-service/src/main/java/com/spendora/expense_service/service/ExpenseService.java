package com.spendora.expense_service.service;

import com.spendora.expense_service.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(Expense expense, String userId);

    List<Expense> getUserExpenses(String userId);

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, Expense expense);

    void deleteExpense(Long id);
}
