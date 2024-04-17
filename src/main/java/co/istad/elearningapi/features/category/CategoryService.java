package co.istad.elearningapi.features.category;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    void createNewCategory(CategoryRequest categoryRequest);

    List<CategoryParentResponse> findAllParentCategories();
    CategoryResponse findAllCategoryByAlias(String alias);
    Page<CategoryResponse> findAllCategoriesByPagination(int page, int size);
    // find all subcategory
    List<Category> findAllSubCategories(Long parentId);



    BaseMessage disableCategoryByAlias(String alias);

    void updateCategoryByAlias(String alias,CategoryRequest request);
}
