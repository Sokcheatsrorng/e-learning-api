package co.istad.elearningapi.features.category;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.features.category.dto.CategoryCreateRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import co.istad.elearningapi.features.category.dto.CategoryUpdateRequest;

public interface CategoryService {
    BaseMessage isDisableCategory(String alias);

    void createCategory(CategoryCreateRequest categoryCreateRequest);

    void updateCategoryByAlias(String alias, CategoryUpdateRequest updateRequest);

}
