package hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation;

public record SumByDaysRes(
        int day,
        Long totalCost
) {
}
