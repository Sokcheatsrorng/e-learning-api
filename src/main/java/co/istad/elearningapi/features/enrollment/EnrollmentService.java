package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;

public interface EnrollmentService {
    void createNewEnrollment(EnrollmentCreateRequest request);

}
