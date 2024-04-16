package co.istad.elearningapi.features.category;

import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import co.istad.elearningapi.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createNewCategory(CategoryRequest request) {

        Category category = categoryRepository.findByAlias(request.alias())
                .orElseThrow();

        if (category.getAlias().equals(request.alias())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category alias has already been existed"
            );
        }

        Category newCategory = categoryMapper.fromCategoryRequest(request);

        newCategory.setDeleted(false);

        categoryRepository.save(newCategory);

    }

    @Override
    public List<CategoryParentResponse> findAllParentCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categoryMapper.toCategoryParentResponseList(categories);
    }
}
