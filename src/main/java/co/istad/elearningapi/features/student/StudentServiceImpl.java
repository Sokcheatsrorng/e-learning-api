package co.istad.elearningapi.features.student;


import co.istad.elearningapi.features.student.dto.StudentCreateRequest;
import co.istad.elearningapi.features.student.dto.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{

    @Override
    public StudentResponse createStudent(StudentCreateRequest studentCreateRequest) {
        log.info("Creating a new student");
        // check user has or not
        return null;
    }

}
