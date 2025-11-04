package hu.Szebi.demoCostManagerApp.data.repositories;

import hu.Szebi.demoCostManagerApp.data.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName (String name);

}
