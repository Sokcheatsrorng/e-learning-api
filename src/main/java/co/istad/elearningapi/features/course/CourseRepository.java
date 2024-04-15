package co.istad.elearningapi.features.course;

import co.istad.elearningapi.domain.Course;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByAlias(String alias);

    boolean existsByAlias(String alias);

    @Modifying
    @Query("UPDATE Course as c SET c.isDeleted = TRUE WHERE c.alias = ?1")
    void disableCourseByAlias(String alias);


}
