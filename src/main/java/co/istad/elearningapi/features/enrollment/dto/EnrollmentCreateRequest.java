package co.istad.elearningapi.features.enrollment.dto;

import co.istad.elearningapi.domain.Course;
import co.istad.elearningapi.domain.Student;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record EnrollmentCreateRequest(
        @NotNull(message = "Code is required")
        String code,
        LocalTime enrollmentAt,
        boolean isDeleted,
        int progress,
        Student student,
        Course course
) {
}