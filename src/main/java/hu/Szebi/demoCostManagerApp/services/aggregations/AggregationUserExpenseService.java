package hu.Szebi.demoCostManagerApp.services.aggregations;

import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;

import java.util.List;

public interface AggregationUserExpenseService {

    List<UserExpenseDtoResponse> listByCategoryId(Long categoryId, Long userId);
    List<UserExpenseDtoResponse> listByMonth(Long userId, int month);
    List<UserExpenseDtoResponse> listByYear(Long userId, int year);
    List<UserExpenseDtoResponse> listByDay(Long userId, Integer day);


    List<YearStatsDto> listYearStats(Long userId, int year);
    List<ByMonthStatsDto> listYearByMonth(Long userId, int year);
    List<CategoryStatsDto> listYearByCategories(Long userId, int year);

    List<ByDayStatsDto> listMonthByDays(Long userId, int year, int month);
    List<CategoryStatsDto> listMonthByCategories(Long userId, int year, int month);
    List<ByMonthStatsDto> listMonthStats(Long userId, int year,int month);

    List<CategoryStatsDto> listDayByCategories(Long userId, int year, int month , int day);
    List<ByDayStatsDto> listDayStats(Long userId, int year, int month, int day);
}
