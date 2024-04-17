package co.istad.elearningapi.features.category.dto;


import java.util.List;

public record CategoryParentResponse(
        String alias,
        String name,
        String icon,
        List<String> categories
) {
}
