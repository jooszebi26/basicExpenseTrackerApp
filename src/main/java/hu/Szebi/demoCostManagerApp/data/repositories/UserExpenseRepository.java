package hu.Szebi.demoCostManagerApp.data.repositories;

import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserExpenseRepository extends JpaRepository<UserExpenseEntity, Long> {

    List<UserExpenseEntity> findByExpenseCategoryId(Long categoryId);
    Optional<List<UserExpenseEntity>> findByUserIdAndExpenseCategoryId(Long userId, Long categoryId);
    List<UserExpenseEntity> findByUserId(Long userId);
}
