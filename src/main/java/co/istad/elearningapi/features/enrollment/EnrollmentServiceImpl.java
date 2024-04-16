package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.domain.Student;
import co.istad.elearningapi.domain.User;
import co.istad.elearningapi.features.course.CourseRepository;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.student.StudentRepository;
import co.istad.elearningapi.mapper.EnrollmentMapper;
import co.istad.elearningapi.util.RandomCodeUtil;
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
    private final StudentRepository studentRepository;
    private final EnrollmentMapper enrollmentMapper;


    @Override
    public void createNewEnrollment(EnrollmentCreateRequest request) {
        // check if course is deleted
        Course course = courseRepository.findByAlias(request.courseAlias()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found")
        );
        // if student enroll this course can't enroll this course again
        if(enrollmentRepository.existsByStudentIdAndCourseAlias(request.student().getId(), course.getAlias())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Enrollment already exists");
        };

        //  student info
        Student student = new Student();
        student.setIsBlocked(false);
        student.setId(request.student().getId());

        // Check if the highSchool field is null in the request
        if (request.student().getHighSchool() == null) {
            student.setUniversity(request.student().getUniversity());
        }

        if (request.student().getHighSchool() != null) {
            student.setHighSchool(request.student().getHighSchool());
        }
        studentRepository.save(student);

        // course info
        course.setAlias(request.courseAlias());
        courseRepository.save(course);
        // enroll
        Enrollment enrollment = enrollmentMapper.formEnrollmentCreateRequest(request);
        enrollment.setCode(RandomCodeUtil.generateRandomCode());
        enrollment.setEnrolledAt(LocalTime.now());
        enrollment.setDeleted(false);
        enrollment.setProgress(0);

        if(enrollment.getProgress()== 100){
            enrollment.setCertified(true);
        }else {
            enrollment.setCertified(false);
        }

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);

    }
}
