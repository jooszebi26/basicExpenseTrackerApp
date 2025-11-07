package hu.Szebi.demoCostManagerApp.services.aggregations;

public record ByMonthStatsDto(int month, long total, double avg, long count) {}
