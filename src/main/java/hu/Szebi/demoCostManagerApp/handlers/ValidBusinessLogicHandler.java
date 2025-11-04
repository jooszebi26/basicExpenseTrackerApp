package hu.Szebi.demoCostManagerApp.handlers;

import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.UserExpenseRepository;
import hu.Szebi.demoCostManagerApp.data.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class ValidBusinessLogicHandler {

    public <E> E findByIdOr404(JpaRepository<E, Long> repo, Long id, String name) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, name + " not found"));
    }

    public <E> void deleteByIdOr404(JpaRepository<E, Long> repo, Long id, String name) {
        var entity = findByIdOr404(repo, id, name);
        repo.delete(entity);
    }

    public UserEntity findByEmailOr404(UserRepository repo, String email, String name) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, name + " email is wrong"));
    }

    public List<UserExpenseEntity> findByUserIdAndCategoryId(UserExpenseRepository repo, Long userId, Long categoryId, String name) {
        return repo.findByUserIdAndExpenseCategoryId(userId, categoryId)
                .orElseThrow(  () -> new ResponseStatusException(HttpStatus.NOT_FOUND, name + " not found"));
    }


}
