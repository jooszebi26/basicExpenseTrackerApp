package hu.Szebi.demoCostManagerApp.data.repositories;

import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserExpenseRepository extends JpaRepository<UserExpenseEntity, Long> {

    List<UserExpenseEntity> findByExpenseCategoryId(Long categoryId);
    List<UserExpenseEntity> findByUserId(Long userId);
    Optional<UserExpenseEntity> findByUserIdAndId(Long userId, Long id);
    Optional<List<UserExpenseEntity>> findByUserIdAndExpenseCategoryId(Long userId, Long categoryId);
    void deleteByUserIdAndExpenseCategoryId(Long userId, Long categoryId);

    @Query("""
        SELECT e 
        FROM UserExpenseEntity e 
        WHERE e.user.id = :userId 
          AND YEAR(e.expenseDate) = :year 
    """)
    List<UserExpenseEntity> findByUserIdAndYear(
            @Param("userId") Long userId,
            @Param("year") int year
    );

    @Query("""
        SELECT e 
        FROM UserExpenseEntity e 
        WHERE e.user.id = :userId 
          AND MONTH(e.expenseDate) = :month
    """)
    List<UserExpenseEntity> findByUserIdAndMonth(
            @Param("userId") Long userId,
            @Param("month") int month
    );

    @Query("""
        SELECT e 
        FROM UserExpenseEntity e 
        WHERE e.user.id = :userId 
          AND DAY(e.expenseDate) = :day
    """)
    List<UserExpenseEntity> findByUserIdAndDay(
            @Param("userId") Long userId,
            @Param("day") int day
    );

    @Query("""
        SELECT YEAR(e.expenseDate), sum(e.cost)
        FROM UserExpenseEntity e 
        WHERE e.user.id = :userId AND YEAR (e.expenseDate) = :year   
    """)

    Integer[] sumGivenYear(
            @Param("userId") Long userId,
            @Param("year") int year
    );

    @Query("""
        SELECT MONTH(e.expenseDate), sum(e.cost)
        FROM UserExpenseEntity e 
        WHERE e.user.id = :userId AND YEAR (e.expenseDate) = :year
        GROUP BY MONTH(e.expenseDate)     
    """)
    List<Integer[]> sumGivenYearByMonth(
            @Param("userId") Long userId,
            @Param("year") int year
    );








}
