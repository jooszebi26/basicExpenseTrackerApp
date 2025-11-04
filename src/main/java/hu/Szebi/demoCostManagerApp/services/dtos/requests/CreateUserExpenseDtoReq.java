package hu.Szebi.demoCostManagerApp.services.dtos.requests;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateUserExpenseDtoReq(
        @PositiveOrZero Long user_id,
        @PositiveOrZero Long category_id,
        @NotNull @Positive Integer cost,
        @NotNull @PastOrPresent LocalDate expense_date,
        @NotNull String comment
) {
}
