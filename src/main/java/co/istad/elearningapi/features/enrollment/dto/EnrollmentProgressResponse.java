package co.istad.elearningapi.features.enrollment.dto;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.features.course.dto.CourseResponse;
import lombok.AllArgsConstructor;

public record EnrollmentProgressResponse(
        int progress,
        CourseResponse course
) {
}
