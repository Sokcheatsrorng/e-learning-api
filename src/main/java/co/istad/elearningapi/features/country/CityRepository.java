package co.istad.elearningapi.features.country;

import co.istad.elearningapi.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {

    @Query("SELECT c FROM City c WHERE c.name = ?1")
    List<CityRepository> findCitiesByName(String name);
    List<City> findByName(String name);

    Page<City> findByNameContainingIgnoreCase(String name, PageRequest pageRequest);
}
