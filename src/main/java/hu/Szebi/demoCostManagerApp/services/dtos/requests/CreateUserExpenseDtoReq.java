package hu.Szebi.demoCostManagerApp.services.dtos.requests;

import java.time.LocalDate;

public record CreateUserExpenseDtoReq(
        Long user_id,
        Long category_id,
        Integer cost,
        LocalDate expense_date,
        String comment
) {
}
