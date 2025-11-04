package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ExpenseCategoryController {

    List<ExpenseCategoryDtoResponse> getExpenseCategories();
    ExpenseCategoryDtoResponse getExpenseCategoryById(@PathVariable long expense_category_id);

}
