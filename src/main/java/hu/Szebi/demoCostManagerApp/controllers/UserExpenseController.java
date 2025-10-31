package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.services.UserExpenseService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user_expense")
@RequiredArgsConstructor
public class UserExpenseController {

    final UserExpenseService userExpenseService;

    @GetMapping("/")
    public List<UserExpenseDtoResponse> getUserExpenses() {
        return userExpenseService.findAll();
    }


    @GetMapping("/{user_expense_id}")
    public UserExpenseDtoResponse getUserExpenses(@PathVariable long user_expense_id) {
        return userExpenseService.findById(user_expense_id);
    }

    @GetMapping("by/category/{category_id}")
    public List<UserExpenseDtoResponse> getUserExpensesByCategoryId(@PathVariable long category_id) {
        return userExpenseService.listByCategoryId(category_id);
    }

    @GetMapping("by/user/{user_id}")
    public List<UserExpenseDtoResponse> getUserExpensesByUserId(@PathVariable long user_id) {
        return userExpenseService.listByUserId(user_id);
    }

    @PostMapping("/create")
    public UserExpenseDtoResponse createUserExpense(@RequestBody CreateUserExpenseDtoReq req) {
        return userExpenseService.save(req);

    }

    @PatchMapping("/update/{userExpense_id}")
    public UserExpenseDtoResponse updateUserExpenseById(@PathVariable long userExpense_id, @RequestBody CreateUserExpenseDtoReq req) {
        return userExpenseService.updateById(req, userExpense_id);
    }

    @DeleteMapping("/delete/{user_expense_id}")
    public void deleteUserExpenseById(@PathVariable long user_expense_id) {
        userExpenseService.deleteById(user_expense_id);
    }

}
