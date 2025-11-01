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
    public List<UserExpenseDtoResponse> findAll() {
        return userExpenseMapper.userExpenseEntitiesToDtos(userExpenseRepo.findAll());
    }

    @Override
    public UserExpenseDtoResponse findById(Long userExpenseId) {
        var e = validBusinessLogicHandler.findByIdOr404(userExpenseRepo, userExpenseId, "UserExpense");
        return userExpenseMapper.userExpenseEntityToDto(e);
    }

    @Override
    public List<UserExpenseDtoResponse> listByCategoryId(Long categoryId) {
        validBusinessLogicHandler.findByIdOr404(expenseCategoryRepo, categoryId, "ExpenseCategory");
        return userExpenseMapper.userExpenseEntitiesToDtos(userExpenseRepo.findByExpenseCategoryId(categoryId));
    }

    @Override
    public List<UserExpenseDtoResponse> listByUserId(Long userId) {
        validBusinessLogicHandler.findByIdOr404(userRepo, userId, "User");
        return userExpenseMapper.userExpenseEntitiesToDtos(userExpenseRepo.findByUserId(userId));
    }

    @Override
    public UserExpenseDtoResponse save(CreateUserExpenseDtoReq req) {
        UserExpenseEntity e = new UserExpenseEntity();
        UserEntity userEntity = userRepo.findById(req.user_id()).orElse(null);
        ExpenseCategoryEntity expenseCategoryEntity = expenseCategoryRepo.findById(req.category_id()).orElse(null);

        e.setExpenseCategory(expenseCategoryEntity);
        e.setUser(userEntity);
        e.setCost(req.cost());
        e.setExpenseDate(req.expense_date());
        e.setComment(req.comment());

        UserExpenseEntity saved = userExpenseRepo.save(e);
        return userExpenseMapper.userExpenseEntityToDto(saved);
    }

    @Override
    public UserExpenseDtoResponse updateById(CreateUserExpenseDtoReq req, Long userExpenseId) {
        UserExpenseEntity e = validBusinessLogicHandler.findByIdOr404(userExpenseRepo, userExpenseId, "UserExpense");
        UserEntity userEntity;
        if (req.user_id() != null) {
            userEntity = validBusinessLogicHandler.findByIdOr404(userRepo, req.user_id(), "User");
        }
        ExpenseCategoryEntity expenseCategoryEntity;
        if (req.category_id() != null) {
            expenseCategoryEntity = validBusinessLogicHandler.findByIdOr404(expenseCategoryRepo, req.category_id(), "ExpenseCategory");
        }
        e.setCost(req.cost());
        e.setExpenseDate(req.expense_date());
        e.setComment(req.comment());

        UserExpenseEntity updated = userExpenseRepo.save(e);
        return userExpenseMapper.userExpenseEntityToDto(updated);

    }

    @Override
    public void deleteById(Long userExpenseId) {
        validBusinessLogicHandler.deleteByIdOr404(userExpenseRepo, userExpenseId, "UserExpense");
    }
}
