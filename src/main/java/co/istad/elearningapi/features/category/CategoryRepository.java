package co.istad.elearningapi.features.category;

import co.istad.elearningapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByAlias(String alias);

}
