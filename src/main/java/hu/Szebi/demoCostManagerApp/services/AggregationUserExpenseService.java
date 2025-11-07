package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumByDaysRes;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumByMonthsRes;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumByCategoriesRes;

import java.util.List;

public interface AggregationUserExpenseService {

    List<UserExpenseDtoResponse> listByCategoryId(Long categoryId, Long userId);
    List<UserExpenseDtoResponse> listByMonth(Long userId, int month);
    List<UserExpenseDtoResponse> listByYear(Long userId, int year);
    List<UserExpenseDtoResponse> listByDay(Long userId, Integer day);
    List<SumByMonthsRes> sumGivenYear(Long userId, int year);
    List<SumByMonthsRes> sumGivenYearByMonth(Long userId, int year);
    List<SumByCategoriesRes> sumGivenYearByCategories(Long userId, int year);
    List<SumByDaysRes> sumGivenMonthByDays(Long userId, int year, int month);
    List<SumByCategoriesRes> sumGivenMonthByCategories(Long userId, int year, int month);
    List<SumByDaysRes> sumGivenDay(Long userId, int year, int month ,int day);
    List<SumByCategoriesRes> sumGivenDayByCategories(Long userId, int year, int month ,int day);

}
