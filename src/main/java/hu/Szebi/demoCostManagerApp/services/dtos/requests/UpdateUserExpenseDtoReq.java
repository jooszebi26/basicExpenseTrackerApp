package hu.Szebi.demoCostManagerApp.services.dtos.requests;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateUserExpenseDtoReq(

        @PositiveOrZero Long user_id,
        @PositiveOrZero Long category_id,
        @Positive Integer cost,
        @PastOrPresent LocalDate expense_date,
        String comment

) {
}
