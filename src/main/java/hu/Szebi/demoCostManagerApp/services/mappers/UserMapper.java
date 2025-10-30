package hu.Szebi.demoCostManagerApp.services.mappers;

import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserDtoResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDtoResponse userEntityToDto(UserEntity e);
    List<UserDtoResponse> userEntitiesToDtos(List<UserEntity> l);

}
