package hu.Szebi.demoCostManagerApp.controllers.impls;

import hu.Szebi.demoCostManagerApp.services.AuthenticationService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.LoginDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/")
    public String login(@Valid @RequestBody LoginDto req) {
        return authenticationService.login(req);
    }

    @PostMapping("/")
    public void  registration(@Valid @RequestBody CreateUserDtoReq req) {
        authenticationService.registration(req);
    }

}
