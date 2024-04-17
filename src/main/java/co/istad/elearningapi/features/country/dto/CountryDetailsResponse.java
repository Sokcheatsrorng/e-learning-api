package co.istad.elearningapi.features.country.dto;

import java.util.List;

public record CountryDetailsResponse(
    CountryResponse country,
    List<CityResponse> city
) {
}
