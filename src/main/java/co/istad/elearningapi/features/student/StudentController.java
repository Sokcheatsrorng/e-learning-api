package co.istad.elearningapi.features.student;

import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import co.istad.elearningapi.features.student.dto.StudentUpdateRequest;
import co.istad.elearningapi.features.user.dto.UserSnippetResponse;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        return studentService.createStudent(studentCreateRequest);
    }

    // Find all students by pagination
    @GetMapping
    public Page<StudentResponse> findAllStudents(@RequestParam(required = false, defaultValue = "0") int page,
                                                         @RequestParam(required = false, defaultValue = "25") int limit) {
        return studentService.findAllStudents(page,limit);

    }

    // Find a student’s profile by username
    @GetMapping("/{username}")
    public UserSnippetResponse findStudentByUsername(@PathVariable String username) {
       return studentService.findStudentByUsername(username);
    }
    // Update a student’s profile by username
    @PutMapping("/{username}")
    public void updateStudent(@PathVariable String username,
                              @RequestBody StudentUpdateRequest studentUpdateRequest) {
        studentService.updateStudent(username,studentUpdateRequest);
    }


}