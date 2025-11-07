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

    Object[] sumGivenYear(
            @Param("userId") Long userId,
            @Param("year") int year
    );

    //YEARS

    @Query("""
        SELECT MONTH(e.expenseDate), sum(e.cost)
        FROM UserExpenseEntity e 
        WHERE e.user.id = :userId AND YEAR (e.expenseDate) = :year
        GROUP BY MONTH(e.expenseDate)
        ORDER BY sum(e.cost)     
    """)
    List<Object[]> sumGivenYearByMonth(
            @Param("userId") Long userId,
            @Param("year") int year
    );

    @Query("""
        SELECT e.expenseCategory.name, sum(e.cost)
        FROM UserExpenseEntity e
        WHERE e.user.id = :userId and YEAR (e.expenseDate) = :year
        GROUP BY e.expenseCategory.name
        ORDER BY sum(e.cost)
    """)
    List<Object[]> sumGivenYearByCategories(
            @Param("userId") Long userId,
            @Param("year") int year
    );

    //YEARS END

    //MONTHS

    @Query("""
        SELECT DAY(e.expenseDate), sum(e.cost)
        FROM UserExpenseEntity e 
        WHERE e.user.id = :userId AND YEAR(e.expenseDate) = :year AND MONTH(e.expenseDate) = :month
        GROUP BY DAY(e.expenseDate)
        ORDER BY sum(e.cost)
    """)
    List<Object[]> sumGivenMonthByDays(
            @Param("userId") Long userId,
            @Param("year") int year,
            @Param("month") int month
    );

    @Query("""
        SELECT e.expenseCategory.name, sum(e.cost)
        FROM UserExpenseEntity e
        WHERE e.user.id = :userId AND YEAR(e.expenseDate) = :year AND MONTH(e.expenseDate) = :month
        GROUP BY e.expenseCategory.name
        ORDER BY sum(e.cost)
    """)

    List<Object[]> sumGivenMonthByCategories(
            @Param("userId") Long userId,
            @Param("year") int year,
            @Param("month") int month
    );

    //MONTHS END

    //DAYS

    @Query("""
        SELECT Day(e.expenseDate), sum(e.cost)
        FROM UserExpenseEntity e
        WHERE e.user.id = :userId AND YEAR(e.expenseDate) = :year AND MONTH(e.expenseDate) = :month AND DAY(e.expenseDate) = :day
        GROUP BY DAY(e.expenseDate)
        ORDER BY sum(e.cost)
    """)
    List<Object[]> sumGivenDay(
            @Param("userId") Long userId,
            @Param("year") int year,
            @Param("month") int month,
            @Param("day") int day
    );



    @Query("""
        SELECT e.expenseCategory.name, sum(e.cost)
        FROM UserExpenseEntity e
        WHERE e.user.id = :userId AND YEAR(e.expenseDate) = :year AND MONTH(e.expenseDate) = :month AND DAY(e.expenseDate) = :day
        GROUP BY e.expenseCategory.name
        ORDER BY sum(e.cost)
    """)
    List<Object[]> sumGivenDayByCategories(
            @Param("userId") Long userId,
            @Param("year") int year,
            @Param("month") int month,
            @Param("day") int day
    );

}
