package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.handlers.CustomUserDetails;
import hu.Szebi.demoCostManagerApp.services.UserExpenseService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user_expense")

@RequiredArgsConstructor
public class UserExpenseController {

    private final UserExpenseService userExpenseService;

    @GetMapping("/my_expenses")
    public List<UserExpenseDtoResponse> getMyExpenses(@AuthenticationPrincipal CustomUserDetails user) {
        return userExpenseService.findAll(user.getId());
    }

    @GetMapping("/my_expense/{user_expense_id}")
    public UserExpenseDtoResponse getMyExpense(@PathVariable long user_expense_id,
                                               @AuthenticationPrincipal User user) {
        return userExpenseService.findById(user_expense_id, user.getUsername());
    }

    @GetMapping("/my_expenses/by/category/{category_id}")
    public List<UserExpenseDtoResponse> getMyExpensesByCategoryId(@PathVariable long category_id,
                                                                  @AuthenticationPrincipal CustomUserDetails user) {
        return userExpenseService.listByCategoryId(category_id, user.getId());
    }

    /*
    @GetMapping("by/user/{user_id}")
    public List<UserExpenseDtoResponse> getUserExpensesByUserId(@PathVariable long user_id) {
        return userExpenseService.listByUserId(user_id);
    }
    */

    @PostMapping("/")
    public UserExpenseDtoResponse createMyExpense(@Valid @RequestBody CreateUserExpenseDtoReq req,
                                                  @AuthenticationPrincipal User user) {
        return userExpenseService.save(req, user.getUsername());

    }

    @PatchMapping("/{userExpense_id}")
    public UserExpenseDtoResponse updateMyExpenseById(@PathVariable long userExpense_id,
                                                      @Valid @RequestBody UpdateUserExpenseDtoReq req,
                                                      @AuthenticationPrincipal User user
    ) {
        return userExpenseService.update(req, userExpense_id, user.getUsername());
    }

    @DeleteMapping("/{user_expense_id}")
    public void deleteMyExpenseById(@PathVariable long user_expense_id,
                                    @AuthenticationPrincipal User user
    )
    {
        userExpenseService.deleteById(user_expense_id, user.getUsername());
    }

}
