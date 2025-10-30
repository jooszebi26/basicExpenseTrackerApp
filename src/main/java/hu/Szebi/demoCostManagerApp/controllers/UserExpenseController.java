package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.data.entities.ExpenseCategoryEntity;
import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import hu.Szebi.demoCostManagerApp.data.entities.UserExpenseEntity;
import hu.Szebi.demoCostManagerApp.data.repositories.ExpenseCategoryRepository;
import hu.Szebi.demoCostManagerApp.data.repositories.UserExpenseRepository;
import hu.Szebi.demoCostManagerApp.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user_expense")
@RequiredArgsConstructor
public class UserExpenseController {

    final UserExpenseRepository userExpenseRepo;
    final UserRepository userRepo;
    final ExpenseCategoryRepository expenseCategoryRepo;

    @GetMapping("/")
    public List<UserExpenseEntity> getUserExpenses() {
        return userExpenseRepo.findAll();
    }


    @GetMapping("/{user_expense_id}")
    public UserExpenseEntity getUserExpenses(@PathVariable long user_expense_id) {
        return userExpenseRepo.findById(user_expense_id).orElse(null);
    }

    @GetMapping("by/category/{category_id}")
    public List<UserExpenseEntity> getUserExpensesByCategoryId(@PathVariable long category_id) {
        return userExpenseRepo.findByExpenseCategoryId(category_id);
    }

    @GetMapping("by/user/{user_id}")
    public List<UserExpenseEntity> getUserExpensesByUserId(@PathVariable long user_id) {
        return userExpenseRepo.findByUserId(user_id);
    }

    @PostMapping("/create")
    public UserExpenseEntity createUserExpense(@RequestBody CreateUserExpenseDtoReq req) {
        UserEntity userEntity = userRepo.findById(req.user_id()).orElse(null);
        ExpenseCategoryEntity expenseCategoryEntity = expenseCategoryRepo.findById(req.category_id()).orElse(null);

        UserExpenseEntity userExpenseEntity = new UserExpenseEntity();
        userExpenseEntity.setUser(userEntity);
        userExpenseEntity.setExpenseCategory(expenseCategoryEntity);
        userExpenseEntity.setCost(req.cost());
        userExpenseEntity.setExpenseDate(req.expense_date());
        userExpenseEntity.setComment(req.comment());

        return userExpenseRepo.save(userExpenseEntity);

    }

    @PatchMapping("/update/{userExpense_id}")
    public UserExpenseEntity updateUserExpenseById(@PathVariable long userExpense_id, @RequestBody CreateUserExpenseDtoReq req) {
        UserExpenseEntity userExpenseEntity = userExpenseRepo.findById(userExpense_id).orElse(null);
        if (req.user_id() != null){
            userExpenseEntity.setUser(userRepo.findById(req.user_id()).orElse(null));
        }

        if (req.category_id() != null){
            userExpenseEntity.setExpenseCategory(expenseCategoryRepo.findById(req.category_id()).orElse(null));
        }

        if (req.cost() != null){
            userExpenseEntity.setCost(req.cost());
        }
        if (req.expense_date() != null){
            userExpenseEntity.setExpenseDate(req.expense_date());
        }
        if (req.comment() != null){
            userExpenseEntity.setComment(req.comment());
        }

        return userExpenseRepo.save(userExpenseEntity);
    }

    @DeleteMapping("/delete/{user_expense_id}")
    public void deleteUserExpenseById(@PathVariable long user_expense_id) {
        userExpenseRepo.deleteById(user_expense_id);
    }



}
