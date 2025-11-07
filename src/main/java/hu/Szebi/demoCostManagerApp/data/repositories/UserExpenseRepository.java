package hu.Szebi.demoCostManagerApp.data.repositories;

import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import hu.Szebi.demoCostManagerApp.services.aggregations.*;
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


    // Havi bontás adott évre
    @Query("""
  select FUNCTION('month', e.expenseDate) as month,
         sum(e.cost)                      as total,
         avg(e.cost)                      as avg,
         count(e)                         as count
  from UserExpenseEntity e
  where e.user.id = :userId
    and FUNCTION('year', e.expenseDate) = :year
  group by FUNCTION('month', e.expenseDate)
  order by FUNCTION('month', e.expenseDate)
""")
    List<MonthStatsView> monthlyStats(@Param("userId") Long userId, @Param("year") int year);

    @Query("""
  select FUNCTION('year', e.expenseDate)   as year,
         sum(e.cost)                      as total,
         avg(e.cost)                      as avg,
         count(e)                         as count
  from UserExpenseEntity e
  where e.user.id = :userId
    and FUNCTION('year', e.expenseDate)  = :year
  group by FUNCTION('year', e.expenseDate)
""")
    List<YearStatsView> statsOfYear(
            @Param("userId") Long userId,
            @Param("year")  int year
    );

    // Napi bontás adott év/hónapra
    @Query("""
  select FUNCTION('day', e.expenseDate)  as day,
         sum(e.cost)                      as total,
         avg(e.cost)                      as avg,
         count(e)                         as count
  from UserExpenseEntity e
  where e.user.id = :userId
    and FUNCTION('year', e.expenseDate) = :year
    and FUNCTION('month', e.expenseDate) = :month
  group by FUNCTION('day', e.expenseDate)
  order by FUNCTION('day', e.expenseDate)
""")
    List<DayStatsView> dailyStats(@Param("userId") Long userId,
                                  @Param("year") int year,
                                  @Param("month") int month);

    @Query("""
  select FUNCTION('month', e.expenseDate)   as month,
         sum(e.cost)                      as total,
         avg(e.cost)                      as avg,
         count(e)                         as count
  from UserExpenseEntity e
  where e.user.id = :userId
    and FUNCTION('year', e.expenseDate)  = :year
    and FUNCTION('month', e.expenseDate) = :month
  group by FUNCTION('month', e.expenseDate)
""")
    List<MonthStatsView> statsOfMonth(
            @Param("userId") Long userId,
            @Param("year")  int year,
            @Param("month") int month
    );

    // Kategóriabontás adott évre
    @Query("""
  select e.expenseCategory.name          as name,
         sum(e.cost)                      as total,
         avg(e.cost)                      as avg,
         count(e)                         as count
  from UserExpenseEntity e
  where e.user.id = :userId
    and FUNCTION('year', e.expenseDate) = :year
  group by e.expenseCategory.name
  order by e.expenseCategory.name
""")
    List<CategoryStatsView> categoryStats(@Param("userId") Long userId, @Param("year") int year);

    //Kategóriabontás adott hónapra
    @Query("""
  select e.expenseCategory.name          as name,
         sum(e.cost)                      as total,
         avg(e.cost)                      as avg,
         count(e)                         as count
  from UserExpenseEntity e
  where e.user.id = :userId
    and FUNCTION('year', e.expenseDate)  = :year
    and FUNCTION('month', e.expenseDate) = :month
  group by e.expenseCategory.name
  order by e.expenseCategory.name
""")
    List<CategoryStatsView> categoryStatsByMonth(
            @Param("userId") Long userId,
            @Param("year")  int year,
            @Param("month") int month
    );

    //Kategóriabontás adott napra
    @Query("""
  select e.expenseCategory.name          as name,
         sum(e.cost)                      as total,
         avg(e.cost)                      as avg,
         count(e)                         as count
  from UserExpenseEntity e
  where e.user.id = :userId
    and FUNCTION('year', e.expenseDate)  = :year
    and FUNCTION('month', e.expenseDate) = :month
    and FUNCTION('day', e.expenseDate)   = :day
  group by e.expenseCategory.name
  order by e.expenseCategory.name
""")
    List<CategoryStatsView> categoryStatsByDay(
            @Param("userId") Long userId,
            @Param("year")  int year,
            @Param("month") int month,
            @Param("day")   int day
    );

    @Query("""
  select FUNCTION('day', e.expenseDate)   as day,
         sum(e.cost)                      as total,
         avg(e.cost)                      as avg,
         count(e)                         as count
  from UserExpenseEntity e
  where e.user.id = :userId
    and FUNCTION('year', e.expenseDate)  = :year
    and FUNCTION('month', e.expenseDate) = :month
    and FUNCTION('day', e.expenseDate)   = :day
  group by FUNCTION('day', e.expenseDate)
""")
    List<DayStatsView> statsOfDay(
            @Param("userId") Long userId,
            @Param("year")  int year,
            @Param("month") int month,
            @Param("day")   int day
    );




}
