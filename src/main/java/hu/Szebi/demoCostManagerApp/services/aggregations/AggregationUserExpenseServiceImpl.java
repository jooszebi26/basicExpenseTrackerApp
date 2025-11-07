package hu.Szebi.demoCostManagerApp.services.aggregations;

import hu.Szebi.demoCostManagerApp.data.repositories.UserExpenseRepository;
import hu.Szebi.demoCostManagerApp.handlers.ValidBusinessLogicHandler;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import hu.Szebi.demoCostManagerApp.services.mappers.AggregationStatsMapper;
import hu.Szebi.demoCostManagerApp.services.mappers.UserExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AggregationUserExpenseServiceImpl implements AggregationUserExpenseService {

    private final UserExpenseRepository userExpenseRepo;
    private final UserExpenseMapper userExpenseMapper;
    private final ValidBusinessLogicHandler validBusinessLogicHandler;
    private final AggregationStatsMapper aggregationStatsMapper;


    @Override
    public List<UserExpenseDtoResponse> listByCategoryId(Long categoryId, Long userId) {
        var expenses = validBusinessLogicHandler.findByUserIdAndCategoryId(userExpenseRepo,userId,categoryId, "UserExpense");
        return userExpenseMapper.userExpenseEntitiesToDtos(expenses);
    }

    @Override
    public List<UserExpenseDtoResponse> listByYear(Long userId, int year) {
        var expenses = userExpenseRepo.findByUserIdAndYear(userId, year);
        return userExpenseMapper.userExpenseEntitiesToDtos(expenses);
    }

    @Override
    public List<UserExpenseDtoResponse> listByDay(Long userId, Integer day) {
        var expenses = userExpenseRepo.findByUserIdAndDay(userId, day);
        return userExpenseMapper.userExpenseEntitiesToDtos(expenses);
    }

    @Override
    public List<YearStatsDto> listYearStats(Long userId, int year) {
        var expenses = userExpenseRepo.statsOfYear(userId, year);
        return aggregationStatsMapper.toYearStatsDtos(expenses);
    }


    @Override
    public List<UserExpenseDtoResponse> listByMonth(Long userId, int month) {
        var expenses = userExpenseRepo.findByUserIdAndMonth(userId, month);
        return userExpenseMapper.userExpenseEntitiesToDtos(expenses);
    }

    @Override
    public List<ByMonthStatsDto> listYearByMonth(Long userId, int year) {
        var expenses = userExpenseRepo.monthlyStats(userId, year);
        return aggregationStatsMapper.toMonthStatsDtos(expenses);
    }

    @Override
    public List<CategoryStatsDto> listYearByCategories(Long userId, int year) {
        var expenses = userExpenseRepo.categoryStats(userId, year);
        return aggregationStatsMapper.toCategoryStatsDtos(expenses);
    }

    @Override
    public List<CategoryStatsDto> listMonthByCategories(Long userId, int year, int month) {
        var expenses = userExpenseRepo.categoryStatsByMonth(userId, year, month);
        return aggregationStatsMapper.toCategoryStatsDtos(expenses);
    }

    @Override
    public List<ByMonthStatsDto> listMonthStats(Long userId, int year, int month) {
        var expenses = userExpenseRepo.statsOfMonth(userId, year, month);
        return aggregationStatsMapper.toMonthStatsDtos(expenses);
    }

    @Override
    public List<ByDayStatsDto> listMonthByDays(Long userId, int year, int month) {
        var expenses = userExpenseRepo.dailyStats(userId, year, month);
        return aggregationStatsMapper.toDayStatsDtos(expenses);
    }


    @Override
    public List<CategoryStatsDto> listDayByCategories(Long userId, int year, int month, int day) {
        var expenses = userExpenseRepo.categoryStatsByDay(userId, year, month, day);
        return aggregationStatsMapper.toCategoryStatsDtos(expenses);
    }

    @Override
    public List<ByDayStatsDto> listDayStats(Long userId, int year, int month, int day) {
        var expenses = userExpenseRepo.statsOfDay(userId, year, month, day);
        return aggregationStatsMapper.toDayStatsDtos(expenses);
    }

}
