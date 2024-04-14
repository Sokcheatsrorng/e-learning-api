package co.istad.elearningapi.features.category;

import co.istad.elearningapi.features.category.dto.CategoryCreateRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;

public interface CategoryService {
    void isDisableCategory(String alias);
    void createCategory(CategoryCreateRequest categoryCreateRequest);

}
