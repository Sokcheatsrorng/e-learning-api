package co.istad.elearningapi.features.country;

import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.features.country.dto.CityNameResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cities")
public class CityController {

    private final CountryService countryService;

    @GetMapping
    Page<CityNameResponse> findAllCities( @RequestParam(required = false) String name,
                                          @RequestParam(
                                                  value = "page",
                                                  required = false,
                                                  defaultValue = "0"
                                          )int page,
                                          @RequestParam(
                                                  value = "size",
                                                  required = false,
                                                  defaultValue = "25"
                                          ) int limit){
        return countryService.findCitiesByName(name, page, limit);
    }

}
