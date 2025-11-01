package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;

import java.util.List;

public interface UserExpenseService {

    List<UserExpenseDtoResponse> findAll();
    UserExpenseDtoResponse findById(Long userExpenseId);
    List<UserExpenseDtoResponse> listByCategoryId(Long categoryId);
    List<UserExpenseDtoResponse> listByUserId(Long userId);
    UserExpenseDtoResponse save(CreateUserExpenseDtoReq req);
    UserExpenseDtoResponse update(UpdateUserExpenseDtoReq req, Long userExpenseId);
    void deleteById (Long userExpenseId);

}
