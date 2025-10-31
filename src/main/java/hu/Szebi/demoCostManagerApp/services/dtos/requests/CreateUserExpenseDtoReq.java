package hu.Szebi.demoCostManagerApp.services.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateUserExpenseDtoReq(
        @NotNull @NotEmpty Long user_id,
        @NotNull @NotEmpty Long category_id,
        @Positive Integer cost,
        @PastOrPresent LocalDate expense_date,
        @NotNull String comment
) {
}
