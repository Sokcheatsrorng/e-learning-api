package co.istad.elearningapi.features.country;

import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.features.country.dto.CityResponse;
import co.istad.elearningapi.features.country.dto.CountryDetailsResponse;
import co.istad.elearningapi.features.country.dto.CountryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {


    Page<CityResponse> findCitiesByName(String name, int page, int limit);

    Page<CountryResponse> findCountriesByName(int page, int limit, String name);

    List<CountryDetailsResponse> findAllCitiesInCountry(String iso);
}
