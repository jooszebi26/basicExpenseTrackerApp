package hu.Szebi.demoCostManagerApp.controllers.impls;

import hu.Szebi.demoCostManagerApp.controllers.ExpenseCategoryController;
import hu.Szebi.demoCostManagerApp.services.ExpenseCategoryService;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/expense_category")
@RequiredArgsConstructor
public class ExpenseCategoryControllerImpl implements ExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;

    @Override
    public List<ExpenseCategoryDtoResponse> getExpenseCategories() {
        return expenseCategoryService.findAll();
    }

    @Override
    public ExpenseCategoryDtoResponse getExpenseCategoryById(long expense_category_id) {
        return expenseCategoryService.findById(expense_category_id);
    }
}
