package hu.Szebi.demoCostManagerApp.services.impls;

import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.UserRepository;
import hu.Szebi.demoCostManagerApp.services.UserService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserDtoResponse;
import hu.Szebi.demoCostManagerApp.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepo;
    final UserMapper userMapper;

    @Override
    public List<UserDtoResponse> findAll() {
        return userMapper.userEntitiesToDtos(userRepo.findAll());
    }

    @Override
    public UserDtoResponse findById(Long userId) {
        return userMapper.userEntityToDto(userRepo.findById(userId).orElse(null));
    }

    @Override
    public UserDtoResponse save(CreateUserDtoReq req) {
        UserEntity e = new UserEntity();
        e.setName(req.name());
        e.setEmail(req.email());
        e.setPassword(req.password());
        UserEntity saved =  userRepo.save(e);
        return userMapper.userEntityToDto(saved);
    }

    @Override
    public void deleteById(Long userId) {
        userRepo.deleteById(userId);
    }
}
