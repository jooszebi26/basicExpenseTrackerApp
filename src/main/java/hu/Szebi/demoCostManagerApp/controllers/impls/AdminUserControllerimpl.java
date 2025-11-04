package hu.Szebi.demoCostManagerApp.controllers.impls;

import hu.Szebi.demoCostManagerApp.controllers.AdminUserController;
import hu.Szebi.demoCostManagerApp.services.UserService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor

public class AdminUserControllerimpl implements AdminUserController {

    private final UserService userService;

    @GetMapping("/")
    @Override
    public List<UserDtoResponse> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{user_id}")
    @Override
    public UserDtoResponse getUser(long user_id) {
        return userService.findById(user_id);
    }

    @PostMapping("/")
    @Override
    public UserDtoResponse createUser(CreateUserDtoReq req) {
        return userService.save(req);
    }

    @DeleteMapping("/")
    @Override
    public void deleteUser(long user_id) {
        userService.deleteById(user_id);
    }
}
