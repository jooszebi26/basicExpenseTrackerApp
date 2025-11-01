package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserDtoResponse;

import java.util.List;

public interface UserService {

    List<UserDtoResponse> findAll();
    UserDtoResponse findById(Long userId);
    UserDtoResponse save(CreateUserDtoReq req);
    UserDtoResponse update(UpdateUserDtoReq req, Long userId);
    void deleteById (Long userId);

}
