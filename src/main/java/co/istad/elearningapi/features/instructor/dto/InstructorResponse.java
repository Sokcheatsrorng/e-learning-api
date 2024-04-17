package co.istad.elearningapi.features.instructor.dto;

import co.istad.elearningapi.features.user.dto.UserDetailsResponse;

import java.util.List;

public record InstructorResponse(
    String biography,
    String github,
    String jobTitle,
    String linkedIn,
    String website,
    List<CourseNameResponse> courses,
    UserDetailsResponse userDetailsResponse
) {
}
