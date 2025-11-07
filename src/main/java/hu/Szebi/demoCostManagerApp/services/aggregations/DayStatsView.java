package hu.Szebi.demoCostManagerApp.services.aggregations;

public interface DayStatsView {

    Integer getDay();
    Long getTotal();
    Double getAvg();
    Long getCount();

}
