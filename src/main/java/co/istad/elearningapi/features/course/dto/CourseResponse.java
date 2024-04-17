package co.istad.elearningapi.features.course.dto;

public record CourseResponse(
        String alias,
        String description,
        String isFree,
        String thumbnail,
        String title
) {
}
