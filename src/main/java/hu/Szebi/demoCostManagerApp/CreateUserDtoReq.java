package hu.Szebi.demoCostManagerApp;

public record CreateUserDtoReq(
        String name,
        String email,
        String password
) {
}
