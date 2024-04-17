package co.istad.elearningapi.features.country;

import co.istad.elearningapi.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,Long> {


    Optional<Country> findByIso(String iso);

    Page<Country> findByNameContainingIgnoreCase(String name, PageRequest pageRequest);
}
