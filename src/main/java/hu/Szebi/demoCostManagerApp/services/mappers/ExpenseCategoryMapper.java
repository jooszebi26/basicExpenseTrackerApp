package hu.Szebi.demoCostManagerApp.services.mappers;

import hu.Szebi.demoCostManagerApp.data.entities.ExpenseCategoryEntity;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseCategoryMapper {

    ExpenseCategoryDtoResponse expenseCategoryEntityToDto(ExpenseCategoryEntity e);
    List<ExpenseCategoryDtoResponse> expenseCategoryEntitiesToDtos(List<ExpenseCategoryEntity> l);
    ExpenseCategoryEntity dtoToExpenseCategoryEntity(CreateExpenseCategoryDtoReq dto);
    List<ExpenseCategoryEntity> dtosToExpenseCategoryEntities(List<CreateExpenseCategoryDtoReq> dtos);

}
