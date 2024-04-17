package co.istad.elearningapi.features.instructor.dto;

import jakarta.validation.constraints.NotBlank;

public record InstructorUpdateRequest(
        @NotBlank(message = "Media is required")
        String mediaName
) {
}
