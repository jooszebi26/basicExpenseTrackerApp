package hu.Szebi.demoCostManagerApp.services.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull @Email String email,
        @NotNull @NotEmpty String password
) {
}
