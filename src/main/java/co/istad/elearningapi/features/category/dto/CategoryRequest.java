package co.istad.elearningapi.features.category.dto;

import co.istad.elearningapi.domain.Category;

public record CategoryRequest(

        String alias,
        String name,
        String icon,
        Long parentCategoryID

) {
}
