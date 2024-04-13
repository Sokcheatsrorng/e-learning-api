package co.istad.elearningapi.features.user;

import co.istad.elearningapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
