package co.istad.elearningapi.features.student;

import co.istad.elearningapi.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
