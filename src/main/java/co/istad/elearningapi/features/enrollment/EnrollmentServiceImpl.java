package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.features.course.CourseRepository;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Slf4j

public class EnrollmentServiceImpl implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;



    @Override
    public void createNewEnrollment(EnrollmentCreateRequest request) {
        // check if course is deleted
        if(courseRepository.existsByAlias(request.course().getAlias())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Course is deleted");
        }
        // complete student info
        Student student = new Student();
        student.setIsBlocked(false);
        student.setUser(request.student().getUser());
        student.setUniversity(request.student().getUniversity());
        student.setHighSchool(request.student().getHighSchool());

    }
}
