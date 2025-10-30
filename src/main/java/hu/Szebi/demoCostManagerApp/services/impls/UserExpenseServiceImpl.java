package hu.Szebi.demoCostManagerApp.services.impls;

import hu.Szebi.demoCostManagerApp.data.repositories.UserExpenseRepository;
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

    //TO DO: implementation

    private final UserExpenseRepository userExpenseRepo;
    private final UserExpenseMapper userExpenseMapper;


    @Override
    public List<UserExpenseDtoResponse> findAll() {
        return List.of();
    }

    @Override
    public UserExpenseDtoResponse findById(Long userId) {
        return null;
    }

    @Override
    public UserExpenseDtoResponse findByCategoryId(Long categoryId) {
        return null;
    }

    @Override
    public UserExpenseDtoResponse findByUserId(Long userId) {
        return null;
    }

    @Override
    public UserExpenseDtoResponse save(CreateUserExpenseDtoReq req) {
        return null;
    }

    @Override
    public UserExpenseDtoResponse updateById(CreateUserExpenseDtoReq req, Long userId) {
        return null;
    }

    @Override
    public void deleteById(Long userExpenseId) {

    }
}
