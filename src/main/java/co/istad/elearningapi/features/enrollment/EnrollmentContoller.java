package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")

public class EnrollmentContoller {
    private final EnrollmentService enrollmentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createEnrollment(@Valid  @RequestBody EnrollmentCreateRequest enrollmentCreateRequest) {
        enrollmentService.createNewEnrollment(enrollmentCreateRequest);
    }
    @GetMapping("/{code}")
    EnrollmentResponse findEnrollmentByCode(@Valid @PathVariable String code) {
        return enrollmentService.findEnrollmentByCode(code);
    }

    @PutMapping("/{code}")
    void updateProgress(@Valid @PathVariable String code,@RequestBody EnrollmentUpdateRequest request) {
        enrollmentService.updateProgress(request, code);
    }

    @GetMapping
    List<EnrollmentResponse> findAll(@Valid
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "") String sort,
            @RequestParam(required = false, defaultValue = "") String code,
            @RequestParam(required = false, defaultValue = "") String courseTitle,
            @RequestParam(required = false, defaultValue = "") String courseCategory,
            @RequestParam(required = false, defaultValue = "") String studentUsername,
            @RequestParam(required = false, defaultValue = "") boolean isCertified
    ) {
        return enrollmentService.findAllEnrollments(
                page,
                size,
                code,
                courseTitle,
                courseCategory,
                studentUsername,
                isCertified
               );
    }


}
