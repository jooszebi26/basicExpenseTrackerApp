package hu.Szebi.demoCostManagerApp.controllers;

import hu.Szebi.demoCostManagerApp.handlers.CustomUserDetails;
import hu.Szebi.demoCostManagerApp.services.UserExpenseService;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.CreateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.requests.UpdateUserExpenseDtoReq;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.UserExpenseDtoResponse;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumByCategoriesRes;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumByDaysRes;
import hu.Szebi.demoCostManagerApp.services.dtos.responses.aggregation.SumByMonthsRes;
import hu.Szebi.demoCostManagerApp.services.impls.AggregationUserExpenseServiceImpl;
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
    private final AggregationUserExpenseServiceImpl aggregationUserExpenseService;

    @GetMapping("/expenses")
    public List<UserExpenseDtoResponse> getMyExpenses(@RequestParam(name = "bycategoryId", required = false) Long categoryId,
                                                      @RequestParam(name="byyear", required = false) Integer year,
                                                      @RequestParam(name = "bymonth", required = false) Integer month,
                                                      @RequestParam(name = "byday", required = false) Integer day,
                                                      @AuthenticationPrincipal CustomUserDetails user)
    {
        if (categoryId != null){
            return aggregationUserExpenseService.listByCategoryId(categoryId, user.getId());
        }
        else if (year != null){
            return aggregationUserExpenseService.listByYear(user.getId(), year);
        }
        else if (month != null) {
            return aggregationUserExpenseService.listByMonth(user.getId(), month);
        }else if(day != null){
            return aggregationUserExpenseService.listByDay(user.getId(), day);
        }else{
            return userExpenseService.findAll(user.getId());
        }

    }


    @GetMapping("/expenses/summary/{expenseYear}")
    public List<SumByMonthsRes> getMyYearlySummaryExpenses(
            @PathVariable(name="expenseYear") Integer year,
            @AuthenticationPrincipal CustomUserDetails user)
    {
        return aggregationUserExpenseService.sumGivenYear(user.getId(), year);
    }

    @GetMapping("/expenses/summary/{expenseYear}/byCategories")
    public List<SumByCategoriesRes> getMyYearlySummaryExpensesByCategories(
            @PathVariable(name="expenseYear") Integer year,
            @AuthenticationPrincipal CustomUserDetails user)
    {
            return aggregationUserExpenseService.sumGivenYearByCategories(user.getId(), year);
    }

    @GetMapping("/expenses/summary/{expenseYear}/byMonths")
    public List<SumByMonthsRes> getMyYearlySummaryExpensesByMonths(
            @PathVariable(name="expenseYear") Integer year,
            @AuthenticationPrincipal CustomUserDetails user)
    {
        return aggregationUserExpenseService.sumGivenYearByMonth(user.getId(), year);
    }

    @GetMapping("/expenses/summary/{expenseYear}/{expenseMonth}/byCategories")
    public List<SumByCategoriesRes> getMyMonthlySummaryExpensesByCategories(
            @PathVariable(name="expenseYear") Integer year,
            @PathVariable(name="expenseMonth") Integer month,
            @AuthenticationPrincipal CustomUserDetails user)
    {
        return aggregationUserExpenseService.sumGivenMonthByCategories(user.getId(), year, month);
    }

    @GetMapping("/expenses/summary/{expenseYear}/{expenseMonth}/byDays")
    public List<SumByDaysRes> getMyMonthlySummaryExpensesByDays(
            @PathVariable(name="expenseYear") Integer year,
            @PathVariable(name="expenseMonth") Integer month,
            @AuthenticationPrincipal CustomUserDetails user)
    {
        return aggregationUserExpenseService.sumGivenMonthByDays(user.getId(), year, month);
    }

    @GetMapping("/expenses/summary/{expenseYear}/{expenseMonth}/{expenseDay}/byCategories")
    public List<SumByCategoriesRes> getMyDailySummaryExpensesByCategories(
            @PathVariable(name="expenseYear") Integer year,
            @PathVariable(name="expenseMonth") Integer month,
            @PathVariable(name = "expenseDay") Integer day,
            @AuthenticationPrincipal CustomUserDetails user
    ){
        return aggregationUserExpenseService.sumGivenDayByCategories(user.getId(), year, month, day);
    }

    @GetMapping("/expenses/summary/{expenseYear}/{expenseMonth}/{expenseDay}")
    public List<SumByDaysRes> getMyDailySummary(
            @PathVariable(name="expenseYear") Integer year,
            @PathVariable(name="expenseMonth") Integer month,
            @PathVariable(name = "expenseDay") Integer day,
            @AuthenticationPrincipal CustomUserDetails user
    ){
        return aggregationUserExpenseService.sumGivenDay(user.getId(), year, month, day);
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
