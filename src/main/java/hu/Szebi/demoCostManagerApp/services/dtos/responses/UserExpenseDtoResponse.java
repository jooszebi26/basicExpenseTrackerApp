package hu.Szebi.demoCostManagerApp.services.dtos.responses;

import java.time.LocalDate;

public record UserExpenseDtoResponse(
        Long id,
        int cost,
        LocalDate expenseDate,
        String comment,
        String category
) {
}
