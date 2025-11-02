package hu.Szebi.demoCostManagerApp.services.impls;

import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.UserRepository;
import hu.Szebi.demoCostManagerApp.handlers.ValidBusinessLogicHandler;
import hu.Szebi.demoCostManagerApp.services.UserService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserDtoResponse;
import hu.Szebi.demoCostManagerApp.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final ValidBusinessLogicHandler validBusinessLogicHandler;

    @Override
    public List<UserDtoResponse> findAll() {
        return userMapper.userEntitiesToDtos(userRepo.findAll());
    }

    @Override
    public UserDtoResponse findById(Long userId) {
        var e = validBusinessLogicHandler.findByIdOr404(userRepo, userId, "User");
        return userMapper.userEntityToDto(e);
    }

    @Override
    public UserDtoResponse save(CreateUserDtoReq req) {
        UserEntity e = userMapper.dtoToUserEntity(req);
        UserEntity saved =  userRepo.save(e);
        return userMapper.userEntityToDto(saved);
    }

    @Override
    public UserDtoResponse update(UpdateUserDtoReq req, Long userId) {
        var e = validBusinessLogicHandler.findByIdOr404(userRepo, userId, "User");
        if (req.name() != null) {
            e.setName(req.name());
        }

        if (req.email() != null) {
            e.setEmail(req.email());
        }

        if (req.password() != null) {
            e.setPassword(req.password());
        }
        return userMapper.userEntityToDto(userRepo.save(e));
    }

    @Override
    public void deleteById(Long userId) {
        var e = validBusinessLogicHandler.findByIdOr404(userRepo, userId, "User");
        userRepo.delete(e);
    }
}
