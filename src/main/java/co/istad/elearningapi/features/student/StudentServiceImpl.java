package co.istad.elearningapi.features.student;


import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import co.istad.elearningapi.features.user.UserRepository;
import co.istad.elearningapi.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse createStudent(StudentCreateRequest studentCreateRequest) {
        log.info("Creating a new student");
        // check user has or not
        User user = userRepository.findByUuid(studentCreateRequest.userUuid())
                .orElseThrow(
                         () -> new ResponseStatusException(
                                 HttpStatus.NOT_FOUND,
                                 "User not found!"
                         )
                );
        Student student = new Student();
        student.setHighSchool(studentCreateRequest.highSchool());
        student.setUniversity(studentCreateRequest.university());
        student.setIsBlocked(false);
        student.setUser(user);
        return studentMapper.toStudentResponse(student);
    }

}
