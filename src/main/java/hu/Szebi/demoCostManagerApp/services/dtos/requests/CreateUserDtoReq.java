package hu.Szebi.demoCostManagerApp.services.dtos.requests;

public record CreateUserDtoReq(
        String name,
        String email,
        String password
) {
}
