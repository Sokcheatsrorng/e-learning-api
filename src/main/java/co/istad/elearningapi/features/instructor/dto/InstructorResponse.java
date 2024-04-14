package co.istad.elearningapi.features.instructor.dto;

import java.util.List;

public record InstructorResponse(
    String biography,
    String github,
    String jobTitle,
    String linkedIn,
    String website,
    List<CourseNameResponse> courses
) {
}
