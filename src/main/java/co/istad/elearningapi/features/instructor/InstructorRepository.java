package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    Optional<Instructor> findById(Long id);
}
