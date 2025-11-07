package hu.Szebi.demoCostManagerApp.services.aggregations;

public interface MonthStatsView {

    Integer getMonth();
    Long getTotal();
    Double getAvg();
    Long getCount();

}
