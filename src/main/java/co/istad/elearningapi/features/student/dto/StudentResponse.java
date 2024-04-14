package co.istad.elearningapi.features.student.dto;
import co.istad.elearningapi.features.user.dto.UserDetailsResponse;
import jakarta.validation.constraints.NotNull;

public record StudentResponse(
        String university,

        String highSchool,

        UserDetailsResponse userDetailsResponse
) {
}
