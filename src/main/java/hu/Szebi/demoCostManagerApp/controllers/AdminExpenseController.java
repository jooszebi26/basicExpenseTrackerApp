package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdminExpenseController extends ExpenseCategoryController {

    ExpenseCategoryDtoResponse createExpenseCategory(@Valid @RequestBody CreateExpenseCategoryDtoReq req);
    void deleteExpenseCategoryById(@PathVariable long expense_category_id);

}
