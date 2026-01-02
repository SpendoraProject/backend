package com.spendora.expense_service.controller;

import com.spendora.expense_service.entity.Expense;
import com.spendora.expense_service.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    //Create Expense
    @PostMapping
    public ResponseEntity<Expense> createExpense(
            @RequestBody Expense expense, @RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(expenseService.createExpense(expense, userId));
    }

    //Get all expenses of logged user
    @GetMapping
    public ResponseEntity<List<Expense>> getUserExpenses(@RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(
                expenseService.getUserExpenses(userId)
        );
    }

    //Get expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    //Update expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense) {

        return ResponseEntity.ok(
                expenseService.updateExpense(id, expense)
        );
    }

    //Delete expense
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense has been deleted Successfully");
    }
}

