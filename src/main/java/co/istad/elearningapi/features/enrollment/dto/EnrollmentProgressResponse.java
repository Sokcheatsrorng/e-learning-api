package co.istad.elearningapi.features.enrollment.dto;

import co.istad.elearningapi.domain.Course;

public record EnrollmentProgressResponse(
        int progress,
        Course course
) {
}
