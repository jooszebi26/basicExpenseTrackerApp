package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserDtoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AdminUserController{

    public List<UserDtoResponse> getUsers();
    UserDtoResponse getUser(@PathVariable long user_id);
    UserDtoResponse createUser(@Valid @RequestBody CreateUserDtoReq req);
    public void deleteUser(@PathVariable long user_id);

}
