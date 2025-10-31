package hu.Szebi.demoCostManagerApp.services.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateExpenseCategoryDtoReq(
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String description
) {
}
