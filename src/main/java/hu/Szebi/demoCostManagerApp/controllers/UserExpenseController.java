package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.handlers.CustomUserDetails;
import hu.Szebi.demoCostManagerApp.services.UserExpenseService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/me")

@RequiredArgsConstructor
public class UserExpenseController {

    private final UserExpenseService userExpenseService;

    @GetMapping("/expenses")
    public List<UserExpenseDtoResponse> getMyExpenses(@RequestParam(name = "categoryId", required = false) Long categoryId,
                                                      @RequestParam(name="year", required = false) Integer year,
                                                      @RequestParam(name = "month", required = false) Integer month,
                                                      @RequestParam(name = "day", required = false) Integer day,
                                                      @AuthenticationPrincipal CustomUserDetails user)
    {
        if (categoryId != null){
            return userExpenseService.listByCategoryId(categoryId, user.getId());
        }
        else if (year != null){
            return userExpenseService.listByYear(user.getId(), year);
        }
        else if (month != null) {
            return userExpenseService.listByMonth(user.getId(), month);
        }else if(day != null){
            return userExpenseService.listByDay(user.getId(), day);
        }else{
            return userExpenseService.findAll(user.getId());
        }

    }

    @GetMapping("/expenses/{expenseId}")
    public UserExpenseDtoResponse getMyExpense(@PathVariable long expenseId,
                                               @AuthenticationPrincipal CustomUserDetails user) {
        return userExpenseService.findById(expenseId, user.getId());
    }

    @PostMapping("/expenses")
    public UserExpenseDtoResponse createMyExpense(@Valid @RequestBody CreateUserExpenseDtoReq req,
                                                  @AuthenticationPrincipal CustomUserDetails user) {
        return userExpenseService.save(req, user.getId());

    }

    @PatchMapping("/expenses/{expenseId}")
    public UserExpenseDtoResponse updateMyExpenseById(@PathVariable long expenseId,
                                                      @Valid @RequestBody UpdateUserExpenseDtoReq req,
                                                      @AuthenticationPrincipal CustomUserDetails user
    ) {
        return userExpenseService.update(req, expenseId, user.getId());
    }

    @DeleteMapping("/expenses/{expenseId}")
    public void deleteMyExpenseById(@PathVariable long expenseId,
                                    @AuthenticationPrincipal CustomUserDetails user
    )
    {
        userExpenseService.deleteById(expenseId, user.getId());
    }

}
