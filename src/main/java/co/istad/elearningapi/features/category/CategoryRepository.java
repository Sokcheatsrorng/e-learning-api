package co.istad.elearningapi.features.category;

import co.istad.elearningapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByAlias(String alias);

    @Modifying
    @Query("UPDATE Category as ca SET ca.isDeleted = TRUE WHERE ca.alias = ?1")
    void disableCategoryByAlias(String alias);

    Optional<Category> findByAlias(String alias);
//    Category findByAlias(String alias);
     List<Category> findByParentCategoryId(long parentId);

}
