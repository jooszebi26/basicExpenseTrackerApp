package hu.Szebi.demoCostManagerApp.services.mappers;

import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDtoResponse userEntityToDto(UserEntity e);
    List<UserDtoResponse> userEntitiesToDtos(List<UserEntity> l);
    UserEntity dtoToUserEntity(CreateUserDtoReq dto);
    List<UserEntity> dtosToUserEntities(List<CreateUserDtoReq> dtos);

}
