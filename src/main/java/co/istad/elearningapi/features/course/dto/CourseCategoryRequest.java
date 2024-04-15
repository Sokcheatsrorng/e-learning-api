package co.istad.elearningapi.features.course.dto;

import co.istad.elearningapi.features.category.dto.CategoryResponse;

public record CourseCategoryRequest(

        String alias,
        String name

) {
}
