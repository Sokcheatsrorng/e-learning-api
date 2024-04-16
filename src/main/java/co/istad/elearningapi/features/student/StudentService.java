package co.istad.elearningapi.features.student;

import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;

public interface StudentService {
    StudentResponse createStudent(StudentCreateRequest studentCreateRequest);
}
