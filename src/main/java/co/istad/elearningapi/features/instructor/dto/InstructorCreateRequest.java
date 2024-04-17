package co.istad.elearningapi.features.instructor.dto;

public record InstructorCreateRequest(
        String biography,
        String github,
        String jobTitle,
        String linkIn,
        String website,
        String username
) {
}
