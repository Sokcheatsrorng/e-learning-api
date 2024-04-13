package co.istad.elearningapi.features.course.dto;

import co.istad.elearningapi.domain.Instructor;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import co.istad.elearningapi.features.instructor.dto.InstructorResponse;

public record CourseDetailsResponse(
        String alias,
        String description,
        Boolean isFree,
        String thumbnail,
        String title,
        InstructorResponse instructor,
        CategoryResponse category
) {
}
