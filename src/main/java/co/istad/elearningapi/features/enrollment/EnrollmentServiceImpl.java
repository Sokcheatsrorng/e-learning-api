package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.domain.*;
import co.istad.elearningapi.features.course.CourseRepository;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentUpdateRequest;
import co.istad.elearningapi.features.student.StudentRepository;
import co.istad.elearningapi.mapper.EnrollmentMapper;
import co.istad.elearningapi.util.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public EnrollmentProgressResponse getProgress(String code) {
        if (code == null || code.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid enrollment code");
        }

        Optional<Enrollment> matchingEnrollment = enrollmentRepository.findAll()
                .stream()
                .filter(enrollment -> code.equals(enrollment.getCode()))
                .findFirst();

        if (matchingEnrollment.isPresent()) {
            int progress = matchingEnrollment.get().getProgress();
            Course course = matchingEnrollment.get().getCourse();
            return new EnrollmentProgressResponse(progress, course);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found");
        }
    }

    @Override
    public void updateCertify(String code) {
        if (code == null || code.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid enrollment code");
        }
        Optional<Enrollment> matchingEnrollment = enrollmentRepository.findByCode(code);
        if (matchingEnrollment.isPresent()) {
            Enrollment enrollment = matchingEnrollment.get();
            if (enrollment.getProgress() == 100) {
                enrollment.setCertified(true);
                enrollmentRepository.save(enrollment);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enrollment progress is not 100%");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found");
        }
    }

    @Override
    public void disableEnrollment(String code) {
        if (code == null || code.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid enrollment code");
        }

        Optional<Enrollment> matchingEnrollment = enrollmentRepository.findByCode(code);

        if (matchingEnrollment.isPresent()) {
            Enrollment enrollment = matchingEnrollment.get();
            enrollment.setDeleted(true);
            enrollmentRepository.save(enrollment);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found");
        }
    }

    @Override
    public EnrollmentResponse findEnrollmentByCode(String code) {

        Enrollment enrollment = enrollmentRepository.findByCode(code).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Enrollment not found")

        );

    return  enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public void updateProgress(EnrollmentUpdateRequest request, String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Enrollment not found!"));
        enrollment.setProgress(request.progress());
        enrollmentRepository.save(enrollment);

    }

    @Override
    public List<EnrollmentResponse> findAllEnrollments(int page, int size,
                                                       String code,
                                                       String courseTitle,
                                                       String courseCategory,
                                                       String studentUsername,
                                                       boolean isCertified) {

        Specification<Enrollment> spec = Specification.where(null);
        if (code != null && !code.isEmpty()){
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("code"), code));
        }
        if (courseTitle != null && !courseTitle.isEmpty()){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("courseTitle"), courseTitle)));
        }
        if (courseCategory != null && !courseCategory.isEmpty()){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("courseCategory"), courseCategory)));
        }
        if (studentUsername != null && !studentUsername.isEmpty()){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("studentUsername"), studentUsername)));
        }
        if (isCertified ){
            spec = spec.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("isCertified"), isCertified)));
        }
       Sort sort = Sort.by(Sort.Direction.ASC, "enrolledAt");

        Pageable pageable = PageRequest.of(page, size, sort);
        List<Enrollment> enrollments = enrollmentRepository.findAll(spec,pageable);

        return enrollmentMapper.toEnrollmentResponseList(enrollments);

    }


}
