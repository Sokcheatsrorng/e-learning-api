package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);

//    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    @Mapping(target = "parentCategory", source = "request.parentCategoryID", qualifiedByName = "mapParentCategory")
    Category fromCategoryRequest(CategoryRequest request);
    @Named("mapParentCategory")
    default Category mapParentCategory(Long parentId) {
        if (parentId == null) {
            return null;
        }
        Category category = new Category();
        category.setId(parentId);
        return category;
    }

    List<CategoryParentResponse> toCategoryParentResponseList(List<Category> category);

}
