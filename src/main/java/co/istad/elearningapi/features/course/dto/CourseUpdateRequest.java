package co.istad.elearningapi.features.course.dto;

public record CourseUpdateRequest(
    String description,
    String title
) {
}
