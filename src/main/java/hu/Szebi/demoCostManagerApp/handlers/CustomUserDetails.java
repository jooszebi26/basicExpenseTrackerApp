package hu.Szebi.demoCostManagerApp.handlers;

import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import lombok.Getter;


@Getter
public class CustomUserDetails extends org.springframework.security.core.userdetails.User{
    private final Long id;
    public CustomUserDetails(UserEntity userEntity) {
        super(userEntity.getUsername(), userEntity.getPassword(),userEntity.getAuthorities());
        this.id = userEntity.getId();
    }

}
