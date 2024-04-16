package co.istad.elearningapi.features.category.dto;

import co.istad.elearningapi.features.category.dto.CategoryResponse;
import lombok.*;

public record CategoryParentResponse(
        String alias,
        String name,
        String icon,
        CategoryResponse category
) {
}
