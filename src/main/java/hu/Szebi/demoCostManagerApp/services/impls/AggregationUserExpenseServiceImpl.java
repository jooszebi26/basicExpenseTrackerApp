package hu.Szebi.demoCostManagerApp.services.impls;

import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.UserExpenseRepository;
import hu.Szebi.demoCostManagerApp.handlers.ValidBusinessLogicHandler;
import hu.Szebi.demoCostManagerApp.services.AggregationUserExpenseService;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumBy;
import hu.Szebi.demoCostManagerApp.services.mappers.UserExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

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
    public List<SumBy> sumGivenYear(Long userId, int year) {
        var expense = userExpenseRepo.sumGivenYear(userId, year);
        return List.of(new SumBy(expense[0], expense[1]));
    }

    @Override
    public List<SumBy> sumGivenYearByMonth(Long userId, int year) {
        var expenses = userExpenseRepo.sumGivenYearByMonth(userId, year);
        return expenses.stream()
                .map(x -> new SumBy(x[0], x[1]))
                .toList();
    }

    @Override
    public List<SumBy> sumByMonthDay(Long userId, int month) {
        var expenses = userExpenseRepo.findByUserIdAndMonth(userId, month);
        var result = expenses.stream()
                .collect(Collectors.groupingBy(x -> x.getExpenseDate().getDayOfMonth(), Collectors.summingInt(UserExpenseEntity::getCost)));

        return result.entrySet().stream()
                .map(entry -> new SumBy(
                        entry.getKey(), entry.getValue()
                ))
                .toList();
    }




}
