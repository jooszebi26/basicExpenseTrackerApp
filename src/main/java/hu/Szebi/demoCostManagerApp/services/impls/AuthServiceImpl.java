package hu.Szebi.demoCostManagerApp.services.impls;

import hu.Szebi.demoCostManagerApp.data.repositories.UserRepository;
import hu.Szebi.demoCostManagerApp.services.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthUserService {

    final UserRepository userRepo;

    @Override
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
            }
        };
    }
}
