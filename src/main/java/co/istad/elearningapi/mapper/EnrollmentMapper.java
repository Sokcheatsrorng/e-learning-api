package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentProgressResponse;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    Enrollment fromEnrollmentCreateRequest(EnrollmentCreateRequest enrollmentCreateRequest);

    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);
    List<EnrollmentResponse> toEnrollmentResponseList(List<Enrollment> enrollments);

    EnrollmentProgressResponse toEnrollmentProgressResponse(Enrollment enrollment);

}
