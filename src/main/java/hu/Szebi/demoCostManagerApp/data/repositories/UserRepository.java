package hu.Szebi.demoCostManagerApp.data.repositories;

import hu.Szebi.demoCostManagerApp.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
