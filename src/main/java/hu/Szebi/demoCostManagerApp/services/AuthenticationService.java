package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.LoginDto;

public interface AuthenticationService {

    String login(LoginDto req);
    void registration(CreateUserDtoReq req);

}
