package co.istad.elearningapi.features.course.dto;

import jakarta.validation.constraints.NotNull;

public record CourseCategoryRequest(

        String alias ,
        @NotNull(message = "Description is required")
        String description,
        boolean isDeleted,
        boolean isFree,
        String thumbnail,
        @NotNull(message = "Instructor is required")
        String instructor,
        @NotNull(message = "Category is required")
        String category
) {
}
