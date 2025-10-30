package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;

import java.util.List;

public interface UserExpenseService {

    List<UserExpenseDtoResponse> findAll();
    UserExpenseDtoResponse findById(Long userId);
    UserExpenseDtoResponse findByCategoryId(Long categoryId);
    UserExpenseDtoResponse findByUserId(Long userId);
    UserExpenseDtoResponse save(CreateUserExpenseDtoReq req);
    UserExpenseDtoResponse updateById(CreateUserExpenseDtoReq req, Long userId);
    void deleteById (Long userExpenseId);

}
