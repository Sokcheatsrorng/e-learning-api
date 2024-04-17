package co.istad.elearningapi.features.country;

import co.istad.elearningapi.features.country.dto.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cities")
public class CityController {

    private final CountryService countryService;

    @GetMapping
    Page<CityResponse> findAllCities(@RequestParam(required = false, defaultValue = "") String name,
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
