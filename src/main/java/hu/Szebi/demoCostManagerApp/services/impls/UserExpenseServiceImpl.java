package hu.Szebi.demoCostManagerApp.services.impls;

import hu.Szebi.demoCostManagerApp.data.entities.ExpenseCategoryEntity;
import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.ExpenseCategoryRepository;
import hu.Szebi.demoCostManagerApp.data.repositories.UserExpenseRepository;
import hu.Szebi.demoCostManagerApp.data.repositories.UserRepository;
import hu.Szebi.demoCostManagerApp.handlers.ValidBusinessLogicHandler;
import hu.Szebi.demoCostManagerApp.services.UserExpenseService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import hu.Szebi.demoCostManagerApp.services.mappers.UserExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserExpenseServiceImpl implements UserExpenseService {

    private final UserExpenseRepository userExpenseRepo;
    private final UserRepository userRepo;
    private final ExpenseCategoryRepository expenseCategoryRepo;
    private final UserExpenseMapper userExpenseMapper;
    private final ValidBusinessLogicHandler validBusinessLogicHandler;


    @Override
    public List<UserExpenseDtoResponse> findAll(Long userId) {
        return userExpenseMapper.userExpenseEntitiesToDtos(userExpenseRepo.findByUserId(userId));
    }

    @Override
    public UserExpenseDtoResponse findById(Long userExpenseId, Long userId) {
        var expense = validBusinessLogicHandler.findByIdOr404(userExpenseRepo, userExpenseId, "UserExpense");
        return userExpenseMapper.userExpenseEntityToDto(expense);
    }

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
    public UserExpenseDtoResponse save(CreateUserExpenseDtoReq req, Long userId) {
        UserExpenseEntity e = new UserExpenseEntity();
        UserEntity userEntity = validBusinessLogicHandler.findByIdOr404(userRepo, userId, "User");
        ExpenseCategoryEntity expenseCategoryEntity = validBusinessLogicHandler.findByIdOr404(
                expenseCategoryRepo,
                req.category_id(),
                "ExpenseCategory");

        e.setExpenseCategory(expenseCategoryEntity);
        e.setUser(userEntity);
        e.setCost(req.cost());
        e.setExpenseDate(req.expense_date());
        e.setComment(req.comment());

        UserExpenseEntity saved = userExpenseRepo.save(e);
        return userExpenseMapper.userExpenseEntityToDto(saved);
    }

    @Override
    public UserExpenseDtoResponse update(UpdateUserExpenseDtoReq req, Long userExpenseId, Long userId) {
        UserExpenseEntity expense = validBusinessLogicHandler.findByUserIdAndUserExpenseId(userExpenseRepo, userId, userExpenseId, "UserExpense");
        validBusinessLogicHandler.findByIdOr404(userRepo, userId, "User");
        ExpenseCategoryEntity expenseCategoryEntity;
        if (req.category_id() != null) {
            expenseCategoryEntity = validBusinessLogicHandler.findByIdOr404(expenseCategoryRepo, req.category_id(), "ExpenseCategory");
        }

        if (req.cost() != null) {
            expense.setCost(req.cost());
        }

        if (req.expense_date() != null) {
            expense.setExpenseDate(req.expense_date());
        }

        if (req.comment() != null) {
            expense.setComment(req.comment());
        }

        UserExpenseEntity updated = userExpenseRepo.save(expense);
        return userExpenseMapper.userExpenseEntityToDto(updated);

    }

    @Override
    public void deleteById(Long userExpenseId, Long userId) {
        validBusinessLogicHandler.deleteByIdOr404(userExpenseRepo, userExpenseId, "UserExpense");
    }
}
