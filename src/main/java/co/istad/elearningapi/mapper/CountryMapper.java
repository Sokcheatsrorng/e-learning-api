package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Country;
import co.istad.elearningapi.features.country.dto.CountryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryResponse toCountryResponse(Country country);

    default Page<CountryResponse> toCountryResponse(Page<Country> countryPage) {
        return countryPage.map(this::toCountryResponse);
    }

}
