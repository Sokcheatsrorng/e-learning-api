package co.istad.elearningapi.features.course.dto;

import jakarta.validation.constraints.NotNull;

public record CourseCreateRequest(
        String alias ,
        @NotNull(message = "Description is required")
        String description,
        boolean isDeleted,
        boolean isFree,
        String thumbnail,
        @NotNull(message = "Instructor is required")
        Long instructorId,
        @NotNull(message = "Category is required")
        Long categoryId,
        @NotNull(message = "Title is required")
        String title
) {
}
