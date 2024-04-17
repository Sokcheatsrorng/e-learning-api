package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    Boolean existsByStudentIdAndCourseAlias(Long id, String alias);
    Optional<Enrollment> findByCode(String code);
    Page<Enrollment> findAll(Specification<Enrollment> spec, Pageable pageable);

}
