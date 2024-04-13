package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.domain.Enrollment;
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
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public void createEnrollment(EnrollmentCreateRequest enrollmentCreateRequest) {
        Enrollment enrollment = new Enrollment();
        // if this course isDeleted can't enroll
        if(enrollment.getCourse().isDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This course is deleted!");
        }
        enrollment = enrollmentMapper.formEnrollmentCreateRequest(enrollmentCreateRequest);
        enrollment.setEnrolledAt(LocalTime.now());
        enrollment.setCode(enrollmentCreateRequest.code());
        enrollment.setCourse(enrollmentCreateRequest.course());
        enrollmentRepository.save(enrollment);
    }
}
