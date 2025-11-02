package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.services.UserService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public List<UserDtoResponse> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{user_id}")
    public UserDtoResponse getUser(@PathVariable long user_id) {
        return userService.findById(user_id);
    }

    @PostMapping("/")
    public UserDtoResponse createUser(@Valid @RequestBody CreateUserDtoReq req) {
        return userService.save(req);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable long user_id) {
        userService.deleteById(user_id);
    }

}
