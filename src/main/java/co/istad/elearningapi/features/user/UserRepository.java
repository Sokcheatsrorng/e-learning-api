package co.istad.elearningapi.features.user;

import co.istad.elearningapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
