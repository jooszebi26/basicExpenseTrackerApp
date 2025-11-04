package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;

import java.util.List;

public interface UserExpenseService {

    List<UserExpenseDtoResponse> findAll(Long userId);
    UserExpenseDtoResponse findById(Long userExpenseId, Long userId);
    List<UserExpenseDtoResponse> listByCategoryId(Long categoryId, Long userId);
    List<UserExpenseDtoResponse> listByMonth(Long userId, int month);
    List<UserExpenseDtoResponse> listByYear(Long userId, int year);
    List<UserExpenseDtoResponse> listByDay(Long userId, Integer day);
    UserExpenseDtoResponse save(CreateUserExpenseDtoReq req, Long userId);
    UserExpenseDtoResponse update(UpdateUserExpenseDtoReq req, Long userExpenseId, Long userId);
    void deleteById (Long userExpenseId, Long userId);

}
