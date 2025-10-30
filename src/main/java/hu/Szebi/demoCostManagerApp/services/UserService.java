package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserDtoResponse;

import java.util.List;

public interface UserService {

    List<UserDtoResponse> findAll();
    UserDtoResponse findById(Long userId);
    UserDtoResponse save(CreateUserDtoReq req);
    void deleteById (Long userId);

}
