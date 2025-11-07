package hu.Szebi.demoCostManagerApp.services.aggregations;

public record ByDayStatsDto(int day, long total, double avg, long count) {}
