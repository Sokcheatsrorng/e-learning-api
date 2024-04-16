package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Enrollment;
import co.istad.elearningapi.features.enrollment.dto.EnrollmentCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

   Enrollment formEnrollmentCreateRequest(EnrollmentCreateRequest enrollmentCreateRequest);
}
