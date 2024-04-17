package co.istad.elearningapi.features.country;

import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.domain.Country;
import co.istad.elearningapi.features.country.dto.CountryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public Page<CountryResponse> findAllCountries(

            @RequestParam(required = false) String name,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0"
            )int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "25"
            ) int limit
    )
    {
        return countryService.findCountriesByName(page,limit,name);
    }

    @GetMapping("/{iso}/cities")
    public List<City> findAllCitiesInCountry(@PathVariable String iso) {
        return countryService.findAllCitiesInCountry(iso);
    }

}
