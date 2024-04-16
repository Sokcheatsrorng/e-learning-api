package co.istad.elearningapi.features.category.dto;

import co.istad.elearningapi.domain.Category;

import java.util.List;

public record CategoryUpdateRequest(
        String alias,
        String name,
        String icon
) {
}
