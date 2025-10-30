package hu.Szebi.demoCostManagerApp.data.repositories;

import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExpenseRepository extends JpaRepository<UserExpenseEntity, Long> {

    List<UserExpenseEntity> findByExpenseCategoryId(Long categoryId);
    List<UserExpenseEntity> findByUserId(Long userId);
}
