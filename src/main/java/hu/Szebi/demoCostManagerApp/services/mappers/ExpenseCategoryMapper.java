package hu.Szebi.demoCostManagerApp.services.mappers;

import hu.Szebi.demoCostManagerApp.data.entities.ExpenseCategoryEntity;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseCategoryMapper {

    ExpenseCategoryDtoResponse expenseCategoryEntityToDto(ExpenseCategoryEntity e);
    List<ExpenseCategoryDtoResponse> expenseCategoryEntitiesToDtos(List<ExpenseCategoryEntity> l);

}
