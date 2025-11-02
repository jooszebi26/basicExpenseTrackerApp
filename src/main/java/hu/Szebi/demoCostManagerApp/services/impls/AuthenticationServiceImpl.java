package hu.Szebi.demoCostManagerApp.services.impls;

import hu.Szebi.demoCostManagerApp.data.entities.RoleEntity;
import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.RoleRepository;
import hu.Szebi.demoCostManagerApp.data.repositories.UserRepository;
import hu.Szebi.demoCostManagerApp.handlers.ValidBusinessLogicHandler;
import hu.Szebi.demoCostManagerApp.services.AuthUserService;
import hu.Szebi.demoCostManagerApp.services.AuthenticationService;
import hu.Szebi.demoCostManagerApp.services.JwtService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final ValidBusinessLogicHandler validBusinessLogicHandler;
    private final AuthUserService authUserService;
    private final JwtService jwtService;

    @Override
    public String login(LoginDto req) {
        var e  = validBusinessLogicHandler.findByEmailOr404(userRepo,req.email(), "User");
        if (!passwordEncoder.matches(req.password(), e.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }

        UserDetails userDetails = authUserService.getUserDetailsService().loadUserByUsername(e.getEmail());
        return jwtService.generateToken(userDetails);
    }

    @Override
    public void registration(CreateUserDtoReq req) {
        if (userRepo.existsByEmail(req.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        RoleEntity roleUser = roleRepo.findByName("ROLE_USER");
        UserEntity u = new UserEntity();
        u.setName(req.name());
        u.setEmail(req.email());
        u.setPassword(passwordEncoder.encode(req.password()));
        u.setRoles(List.of(roleUser));

        userRepo.save(u);
    }
}
