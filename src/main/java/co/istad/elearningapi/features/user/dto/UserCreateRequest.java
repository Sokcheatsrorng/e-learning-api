package co.istad.elearningapi.features.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserCreateRequest(
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "National Id card is required")
        @Size(max = 30)
        String nationalIdCard,

        @NotBlank(message = "Phone number is required")
        @Size(max = 30, message = "Phone number must less than 30 characters")
        String phoneNumber,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Password is required")
        String confirmedPassword,

        @NotBlank(message = "username is required")
        @Size(max = 40, message = "Name must be less than 40 characters")
        String username,

        @NotBlank
        @Size(max = 6)
        String gender,

        @NotNull
        LocalDate dob,

        String address1,

        String address2,

        String givenName,

        String familyName,

        String profile,
        String city,
        String country
) {
}
