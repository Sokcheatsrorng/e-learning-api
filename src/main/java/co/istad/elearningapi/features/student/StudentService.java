package co.istad.elearningapi.features.student;

import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import co.istad.elearningapi.features.student.dto.StudentUpdateRequest;
import co.istad.elearningapi.features.user.dto.UserSnippetResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    StudentResponse createStudent(StudentCreateRequest studentCreateRequest);

    Page<StudentResponse> findAllStudents(int page, int limit);

    UserSnippetResponse findStudentByUsername(String username);

    void updateStudent(String username, StudentUpdateRequest studentUpdateRequest);
}
