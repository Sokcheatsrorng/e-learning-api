package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;

public interface EnrollmentService {

    void createEnrollment(EnrollmentCreateRequest enrollmentCreateRequest);
}
