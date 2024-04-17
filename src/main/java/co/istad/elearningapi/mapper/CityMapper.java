package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.features.country.dto.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityResponse toCityResponse(City city);
}
