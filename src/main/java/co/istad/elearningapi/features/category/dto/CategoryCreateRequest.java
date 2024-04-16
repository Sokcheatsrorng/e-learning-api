package co.istad.elearningapi.features.category.dto;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.domain.Course;

import java.util.List;

public record CategoryCreateRequest(
        String name,
        String alias,
        boolean isDeleted,
        List<Category>parentsCategory
) {
}
