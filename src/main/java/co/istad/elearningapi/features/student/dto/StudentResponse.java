package co.istad.elearningapi.features.student.dto;

import co.istad.elearningapi.features.user.dto.UserDetailResponse;
import jakarta.validation.constraints.NotNull;

public record StudentResponse(
        String university,

        String highSchool,

        UserDetailResponse userResponse
) {
}
