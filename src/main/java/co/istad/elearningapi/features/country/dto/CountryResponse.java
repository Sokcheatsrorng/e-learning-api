package co.istad.elearningapi.features.country.dto;

import jakarta.persistence.Column;

public record CountryResponse(
         String flag,

         String iso,
         String name,

         String niceName,

         int numberCode,

         String phoneCode
) {
}
