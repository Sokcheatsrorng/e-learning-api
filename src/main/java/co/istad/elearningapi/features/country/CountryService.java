package co.istad.elearningapi.features.country;

import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.domain.Country;
import co.istad.elearningapi.features.country.dto.CityNameResponse;
import co.istad.elearningapi.features.country.dto.CountryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CountryService {


    Page<CityNameResponse> findCitiesByName(String name,int page, int limit);

    Page<CountryResponse> findCountriesByName(int page, int limit, String name);

    List<City> findAllCitiesInCountry(String iso);
}
