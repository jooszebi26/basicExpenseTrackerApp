package hu.Szebi.demoCostManagerApp.services.impls;

import hu.Szebi.demoCostManagerApp.data.repositories.UserExpenseRepository;
import hu.Szebi.demoCostManagerApp.handlers.ValidBusinessLogicHandler;
import hu.Szebi.demoCostManagerApp.services.AggregationUserExpenseService;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumByDaysRes;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumByMonthsRes;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumByCategoriesRes;
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
    public List<UserExpenseDtoResponse> listByMonth(Long userId, int month) {
        var expenses = userExpenseRepo.findByUserIdAndMonth(userId, month);
        return userExpenseMapper.userExpenseEntitiesToDtos(expenses);
    }

    @Override
    public List<SumByMonthsRes> sumGivenYear(Long userId, int year) {
        var expense = userExpenseRepo.sumGivenYear(userId, year);
        return List.of(new SumByMonthsRes( (Integer) expense[0], (Long) expense[1]));
    }

    @Override
    public List<SumByMonthsRes> sumGivenYearByMonth(Long userId, int year) {
        var expenses = userExpenseRepo.sumGivenYearByMonth(userId, year);
        return expenses.stream()
                .map(x -> new SumByMonthsRes( (Integer) x[0], (Long) x[1]))
                .toList();
    }

    @Override
    public List<SumByCategoriesRes> sumGivenYearByCategories(Long userId, int year) {
        var expenses = userExpenseRepo.sumGivenYearByCategories(userId, year);
        return expenses.stream()
                .map(x -> new SumByCategoriesRes((String) x[0], (Long) x[1]))
                .toList();
    }

    @Override
    public List<SumByCategoriesRes> sumGivenMonthByCategories(Long userId, int year, int month) {
        var expenses = userExpenseRepo.sumGivenMonthByCategories(userId, year, month);
        return expenses.stream()
                .map(x -> new SumByCategoriesRes((String) x[0], (Long) x[1]))
                .toList();
    }

    @Override
    public List<SumByDaysRes> sumGivenMonthByDays(Long userId, int year, int month) {
        var expenses = userExpenseRepo.sumGivenMonthByDays(userId, year, month);
        return expenses.stream()
                .map(x -> new SumByDaysRes((Integer) x[0], (Long) x[1]))
                .toList();
    }

    @Override
    public List<SumByDaysRes> sumGivenDay(Long userId, int year, int month, int day) {
        var expenses = userExpenseRepo.sumGivenDay(userId, year, month, day);
        return expenses.stream()
                .map(x -> new SumByDaysRes( (Integer) x[0], (Long) x[1]))
                .toList();
    }

    @Override
    public List<SumByCategoriesRes> sumGivenDayByCategories(Long userId, int year, int month, int day) {
        var expenses = userExpenseRepo.sumGivenDayByCategories(userId, year, month, day);
        return expenses.stream()
                .map(x -> new SumByCategoriesRes((String) x[0], (Long) x[1]))
                .toList();
    }

}
