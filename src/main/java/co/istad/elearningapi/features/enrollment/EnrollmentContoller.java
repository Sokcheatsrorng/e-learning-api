package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment")

public class EnrollmentContoller {
    private final EnrollmentService enrollmentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createEnrollment(@RequestBody EnrollmentCreateRequest enrollmentCreateRequest) {
        enrollmentService.createEnrollment(enrollmentCreateRequest);
    }
}
