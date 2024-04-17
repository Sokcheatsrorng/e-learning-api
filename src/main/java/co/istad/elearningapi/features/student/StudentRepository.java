package co.istad.elearningapi.features.student;

import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {


}
