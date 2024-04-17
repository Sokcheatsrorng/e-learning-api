package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.features.country.dto.CityNameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityNameResponse toCityNameResponse(City city);
}
