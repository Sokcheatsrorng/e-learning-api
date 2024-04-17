package co.istad.elearningapi.features.enrollment;

import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentUpdateRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EnrollmentService {
    void createNewEnrollment(EnrollmentCreateRequest request);
    EnrollmentResponse findEnrollmentByCode(String code);
    void updateProgress(EnrollmentUpdateRequest request, String code);
    List<EnrollmentResponse> findAllEnrollments(int page,int size,
                                        Sort sort,
                                        String code,
                                        String courseTitle,
                                        String courseCategory,
                                        String studentUsername,
                                        boolean isCertified);


    EnrollmentProgressResponse getProgress(String code);

    void updateCertify(String code);

    void disableEnrollment(String code);
}
