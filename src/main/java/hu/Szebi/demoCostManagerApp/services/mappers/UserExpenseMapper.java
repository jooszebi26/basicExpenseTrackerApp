package hu.Szebi.demoCostManagerApp.services.mappers;

import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserExpenseMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "expenseCategory.id", target = "expenseCategoryId")
    UserExpenseDtoResponse userExpenseEntityToDto(UserExpenseEntity e);
    List<UserExpenseDtoResponse> userExpenseEntitiesToDtos(List<UserExpenseEntity> l);

    UserExpenseEntity dtoToUserEntity(CreateUserExpenseDtoReq dto);
    List<UserExpenseDtoResponse> dtosToUserEntities(List<CreateUserExpenseDtoReq> dtos);

}
