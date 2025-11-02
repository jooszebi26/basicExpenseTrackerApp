package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;
import hu.Szebi.demoCostManagerApp.services.ExpenseCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense_category")
@RequiredArgsConstructor
public class ExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;

    @GetMapping("/")
    public List<ExpenseCategoryDtoResponse> getExpenseCategories() {
        return expenseCategoryService.findAll();
    }

    @GetMapping("/{expense_category_id}")
    public ExpenseCategoryDtoResponse getExpenseCategoryById(@PathVariable long expense_category_id) {
        return expenseCategoryService.findById(expense_category_id);
    }

    @PostMapping("/")
    public ExpenseCategoryDtoResponse createExpenseCategory(@Valid @RequestBody CreateExpenseCategoryDtoReq req) {
        return expenseCategoryService.save(req);
    }

    @DeleteMapping("/{expense_category_id}")
    public void deleteExpenseCategoryById(@PathVariable long expense_category_id) {
        expenseCategoryService.deleteById(expense_category_id);
    }

}
