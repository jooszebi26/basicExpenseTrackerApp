package hu.Szebi.demoCostManagerApp.services.aggregations;

public interface YearStatsView {
    Integer getYear();
    Long getTotal();
    Double getAvg();
    Long getCount();
}
