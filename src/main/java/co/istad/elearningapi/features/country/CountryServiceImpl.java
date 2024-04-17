package co.istad.elearningapi.features.country;

import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.domain.Country;
import co.istad.elearningapi.features.country.dto.CityNameResponse;
import co.istad.elearningapi.features.country.dto.CountryResponse;
import co.istad.elearningapi.mapper.CityMapper;
import co.istad.elearningapi.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements  CountryService{
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final CountryMapper  countryMapper;
    private final CityMapper cityMapper;

    @Override
    public Page<CityNameResponse> findCitiesByName(String name, int page, int size) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page must be greater than or equal to 0");
        }
        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size must be greater than or equal to 1");
        }
        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sortByName);
        Page<City> citiesPage = cityRepository.findByNameContainingIgnoreCase(name, pageRequest);

        return citiesPage.map(cityMapper::toCityNameResponse);
    }

    @Override
    public Page<CountryResponse> findCountriesByName(int page, int limit, String name) {
        if (page < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page must be greater than or equal to 0"
            );
        }
        if (limit < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Limit must be greater than or equal to 1"
            );
        }
        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(page, limit, sortByName);
        Page<Country> countries;
        if (name != null && !name.isEmpty()) {
            countries = countryRepository.findByNameContainingIgnoreCase(name, pageRequest);
        } else {
            countries = countryRepository.findAll(Pageable.unpaged());
        }
        return countryMapper.toCountryResponse(countries);
    }

    @Override
    public List<City> findAllCitiesInCountry(String iso) {
        Country country = countryRepository.findByIso(iso)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Country has not been found"
                        )
                );
        // Retrieve cities associated with the country
        List<City> cities = country.getCities();
        // If no cities are found, throw an exception
        if (cities.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No cities found for the country"
            );
        }
        return cities;
    }
}
