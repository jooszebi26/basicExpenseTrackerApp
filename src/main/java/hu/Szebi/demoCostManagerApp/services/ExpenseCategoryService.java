package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;

import java.util.List;

public interface ExpenseCategoryService {

    List<ExpenseCategoryDtoResponse> findAll();
    ExpenseCategoryDtoResponse findById(Long categoryId);
    ExpenseCategoryDtoResponse save(CreateExpenseCategoryDtoReq req);
    ExpenseCategoryDtoResponse update(UpdateExpenseCategoryDtoReq req, Long categoryId);
    void deleteById (Long categoryId);

}
