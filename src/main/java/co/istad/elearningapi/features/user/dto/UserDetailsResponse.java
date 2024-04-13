package co.istad.elearningapi.features.user.dto;

import java.time.LocalDate;
import java.util.List;

public record UserDetailsResponse(

        String address1,
        String address2,
        LocalDate dob,
        String email,
        String familyName,
        String gender,
        String givenName,
        Boolean isVerified,
        String nationalIdCard,
        String phoneNumber,
        String profile,
        String username,
        String uuid,
        String verifiedCode,
        List<RoleResponse> roles,
        String countryName,
        String cityName

) {
}
