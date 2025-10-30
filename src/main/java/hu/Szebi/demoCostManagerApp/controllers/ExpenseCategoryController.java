package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.CreateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.data.entities.ExpenseCategoryEntity;
import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.ExpenseCategoryRepository;
import hu.Szebi.demoCostManagerApp.data.repositories.UserExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense_category")
@RequiredArgsConstructor
public class ExpenseCategoryController {

    final ExpenseCategoryRepository expenseCategoryRepo;

    @GetMapping("/")
    public List<ExpenseCategoryEntity> getExpenseCategories() {
        return expenseCategoryRepo.findAll();
    }

    @GetMapping("/{expense_category_id}")
    public ExpenseCategoryEntity getExpenseCategoryById(@PathVariable long expense_category_id) {
        return expenseCategoryRepo.findById(expense_category_id).orElse(null);
    }

    @PostMapping("/create")
    public ExpenseCategoryEntity createExpenseCategory(@RequestBody CreateExpenseCategoryDtoReq req) {
        ExpenseCategoryEntity expenseCategory = new ExpenseCategoryEntity();
        expenseCategory.setName(req.name());
        expenseCategory.setDescription(req.description());
        return expenseCategoryRepo.save(expenseCategory);
    }

    @DeleteMapping("/delete/{expense_category_id}")
    public void deleteExpenseCategory(@PathVariable long expense_category_id) {
        expenseCategoryRepo.deleteById(expense_category_id);
    }

}
