package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;

import java.util.List;

public interface UserExpenseService {

    List<UserExpenseDtoResponse> findAll(Long userId);
    UserExpenseDtoResponse findById(Long userExpenseId, Long userId);
    UserExpenseDtoResponse save(CreateUserExpenseDtoReq req, Long userId);
    UserExpenseDtoResponse update(UpdateUserExpenseDtoReq req, Long userExpenseId, Long userId);
    void deleteById (Long userExpenseId, Long userId);

}
