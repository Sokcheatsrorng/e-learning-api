package co.istad.elearningapi.features.user;

import co.istad.elearningapi.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    List<User> findAll(Specification<User> spec, Sort sort);

    @Modifying
    @Query("UPDATE User as u SET u.isDeleted = TRUE WHERE u.username = ?1")
    void disableByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.isDeleted = false WHERE u.username = ?1")
    void enableUserByUsername(String username);
}
