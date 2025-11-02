package hu.Szebi.demoCostManagerApp.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserService{
    UserDetailsService getUserDetailsService();
}
