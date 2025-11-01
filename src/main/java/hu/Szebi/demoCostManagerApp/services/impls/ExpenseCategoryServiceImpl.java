package hu.Szebi.demoCostManagerApp.services.impls;

import hu.Szebi.demoCostManagerApp.data.entities.ExpenseCategoryEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.ExpenseCategoryRepository;
import hu.Szebi.demoCostManagerApp.handlers.ValidBusinessLogicHandler;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.services.mappers.ExpenseCategoryMapper;
import hu.Szebi.demoCostManagerApp.services.ExpenseCategoryService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateExpenseCategoryDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.ExpenseCategoryDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    final ExpenseCategoryRepository expenseCategoryRepo;
    final ExpenseCategoryMapper expenseCategoryMapper;
    final ValidBusinessLogicHandler validBusinessLogicHandler;

    @Override
    public List<ExpenseCategoryDtoResponse> findAll() {
        List<ExpenseCategoryEntity> expenseCategoryEntities = expenseCategoryRepo.findAll();
        return expenseCategoryMapper.expenseCategoryEntitiesToDtos(expenseCategoryEntities);
    }

    @Override
    public ExpenseCategoryDtoResponse findById(Long categoryId) {
        var e = validBusinessLogicHandler.findByIdOr404(expenseCategoryRepo, categoryId, "ExpenseCategory");
        return expenseCategoryMapper.expenseCategoryEntityToDto(e);
    }

    @Override
    public ExpenseCategoryDtoResponse save(CreateExpenseCategoryDtoReq req) {
        ExpenseCategoryEntity e = new ExpenseCategoryEntity();
        e.setName(req.name());
        e.setDescription(req.description());
        ExpenseCategoryEntity saved = expenseCategoryRepo.save(e);
        return expenseCategoryMapper.expenseCategoryEntityToDto(saved);
    }

    @Override
    public ExpenseCategoryDtoResponse update(UpdateExpenseCategoryDtoReq req, Long categoryId) {
        var e = validBusinessLogicHandler.findByIdOr404(expenseCategoryRepo, categoryId, "ExpenseCategory");
        if (req.name() != null) {
            e.setName(req.name());
        }

        if (req.description() != null) {
            e.setDescription(req.description());
        }

        var saved = expenseCategoryRepo.save(e);
        return expenseCategoryMapper.expenseCategoryEntityToDto(saved);

    }

    @Override
    public void deleteById(Long categoryId) {
        validBusinessLogicHandler.deleteByIdOr404(expenseCategoryRepo, categoryId, "ExpenseCategory");
    }
}
