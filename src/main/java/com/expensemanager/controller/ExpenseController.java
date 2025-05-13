package com.expensemanager.controller;

import com.expensemanager.model.Expense;
import com.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Display the list of all expenses
    @GetMapping("/expenses")
    public String getAllExpenses(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        return "expenses";  // Display the 'expenses' view
    }

    // Show the form to add or edit an expense
    @GetMapping("/expense/form")
    public String showExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "expense_form";  // Display the 'expense_form' view
    }

    // Save a new or existing expense
    @PostMapping("/expense/save")
    public String saveExpense(@ModelAttribute("expense") Expense expense) {
        expenseService.saveExpense(expense);
        return "redirect:/expenses";  // Redirect to the list of expenses after saving
    }

    // Edit an existing expense
    @GetMapping("/expense/edit/{id}")
    public String editExpense(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense Id:" + id));
        model.addAttribute("expense", expense);
        return "expense_form";  // Display the form with the existing expense data
    }

    // Delete an expense
    @GetMapping("/expense/delete/{id}")
    public String deleteExpense(@PathVariable("id") Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";  // Redirect to the list of expenses after deleting
    }
}
