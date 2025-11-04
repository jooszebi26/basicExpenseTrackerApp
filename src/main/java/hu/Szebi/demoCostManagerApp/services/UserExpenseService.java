package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;

import java.util.List;

public interface UserExpenseService {

    List<UserExpenseDtoResponse> findAll(Long userId);
    UserExpenseDtoResponse findById(Long userExpenseId, String email);
    List<UserExpenseDtoResponse> listByCategoryId(Long categoryId, Long userId);
    List<UserExpenseDtoResponse> listByUserId(Long userId);
    List<UserExpenseDtoResponse> listByUserEmail(String email);
    UserExpenseDtoResponse save(CreateUserExpenseDtoReq req, String email);
    UserExpenseDtoResponse update(UpdateUserExpenseDtoReq req, Long userExpenseId, String email);
    void deleteById (Long userExpenseId, String email);

}
