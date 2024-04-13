package co.istad.elearningapi.features.course;

import co.istad.elearningapi.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByAlias(String alias);

    boolean existsByAlias(String alias);

}
