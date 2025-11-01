package hu.Szebi.demoCostManagerApp.services.dtos.requests;

public record UpdateUserDtoReq(
        String name,
        String email,
        String password
) {
}
