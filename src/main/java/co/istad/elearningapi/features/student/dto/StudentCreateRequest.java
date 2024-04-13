package co.istad.elearningapi.features.student.dto;

import jakarta.validation.constraints.NotNull;

public record StudentCreateRequest(

        @NotNull(message = "HighSchool Information is required.")
        String highSchool,

        @NotNull(message = "University Information is required.")
        String university,

        // we use uuid of user to create student role
        @NotNull(message="UUID of user is required")
        String userUuid

) {
}
