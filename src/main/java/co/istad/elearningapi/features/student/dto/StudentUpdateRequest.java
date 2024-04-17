package co.istad.elearningapi.features.student.dto;

import jakarta.validation.constraints.NotBlank;

public record StudentUpdateRequest(
        @NotBlank(message = "Media is required")
        String mediaName
) {
}
