package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import co.istad.elearningapi.features.course.dto.CourseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);

}
