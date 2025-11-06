package hu.Szebi.demoCostManagerApp.services;

import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumBy;

import java.util.List;

public interface AggregationUserExpenseService {

    List<UserExpenseDtoResponse> listByCategoryId(Long categoryId, Long userId);
    List<UserExpenseDtoResponse> listByMonth(Long userId, int month);
    List<UserExpenseDtoResponse> listByYear(Long userId, int year);
    List<UserExpenseDtoResponse> listByDay(Long userId, Integer day);
    List<SumBy> sumGivenYear(Long userId, int year);
    List<SumBy> sumGivenYearByMonth(Long userId, int year);
    List<SumBy> sumByMonthDay(Long userId, int month);

}
