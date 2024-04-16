package co.istad.elearningapi.features.category.dto;

public record CategoryRequest(

        String alias,
        String name,
        String icon,
        Long parentCategoryID
) {
}
