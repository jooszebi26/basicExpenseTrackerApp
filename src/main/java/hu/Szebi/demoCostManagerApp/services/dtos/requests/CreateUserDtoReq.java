package hu.Szebi.demoCostManagerApp.services.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateUserDtoReq(
        @NotNull @NotEmpty String name,
        @Email String email,
        @NotNull @NotEmpty String password
) {
}
