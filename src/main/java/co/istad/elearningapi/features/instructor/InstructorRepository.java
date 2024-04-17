package co.istad.elearningapi.features.instructor;

import co.istad.elearningapi.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
