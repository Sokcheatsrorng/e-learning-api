package co.istad.elearningapi.features.category;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;

import java.util.List;

public interface CategoryService {

    void createNewCategory(CategoryRequest request);

    List<CategoryParentResponse> findAllParentCategories();
    BaseMessage disableCategoryByAlias(String alias);

    void updateCategoryByAlias(String alias,CategoryRequest request);
}
