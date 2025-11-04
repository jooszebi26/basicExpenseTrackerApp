package hu.Szebi.demoCostManagerApp.controllers.impls;

import hu.Szebi.demoCostManagerApp.controllers.AdminExpenseController;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;
import hu.Szebi.demoCostManagerApp.services.ExpenseCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/expense_category")
@RequiredArgsConstructor
public class AdminExpenseCategoryControllerImpl implements AdminExpenseController {

    private final ExpenseCategoryService expenseCategoryService;


    @Override
    @GetMapping("/")
    public List<ExpenseCategoryDtoResponse> getExpenseCategories() {
        return expenseCategoryService.findAll();
    }

    @Override
    @GetMapping("/{expense_category_id}")
    public ExpenseCategoryDtoResponse getExpenseCategoryById(@PathVariable long expense_category_id) {
        return expenseCategoryService.findById(expense_category_id);
    }

    @Override
    @PostMapping("/")
    public ExpenseCategoryDtoResponse createExpenseCategory(@Valid @RequestBody CreateExpenseCategoryDtoReq req) {
        return expenseCategoryService.save(req);
    }

    @Override
    @DeleteMapping("/{expense_category_id}")
    public void deleteExpenseCategoryById(@PathVariable long expense_category_id) {
        expenseCategoryService.deleteById(expense_category_id);
    }

}
