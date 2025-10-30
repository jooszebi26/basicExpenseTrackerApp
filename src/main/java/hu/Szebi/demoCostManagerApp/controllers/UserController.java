package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    final UserRepository userRepo;

    @GetMapping("/")
    public List<UserEntity> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/{user_id}")
    public UserEntity getUser(@PathVariable long user_id) {
        return userRepo.findById(user_id).orElse(null);
    }

    @PostMapping("/create")
    public UserEntity createUser(@RequestBody CreateUserDtoReq req) {
        UserEntity user = new UserEntity();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(req.password());
        return userRepo.save(user);
    }

    @DeleteMapping("/delete/{user_id}")
    public void deleteUser(@PathVariable long user_id) {
        userRepo.deleteById(user_id);
    }

}
