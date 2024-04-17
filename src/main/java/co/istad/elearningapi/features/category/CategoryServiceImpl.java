package co.istad.elearningapi.features.category;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import co.istad.elearningapi.mapper.CategoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.resource.transaction.backend.jta.internal.JtaTransactionAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void updateCategoryByAlias(String alias, CategoryRequest updateRequest){

        if (categoryRepository.existsByAlias(alias)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found"
            );
        }

        Category category = categoryRepository.findByAlias(alias)
                        .orElseThrow(
                                () -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Category has not been found!"
                                )
                        );
        Category parentCategory = categoryRepository.findById(updateRequest.parentCategoryID())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Parent Category has not been found"
                        )
                );
        category.setName(updateRequest.name());
        category.setParentCategory(parentCategory);
        category.setIcon(updateRequest.icon());

        categoryRepository.save(category);
    }

    @Override
    public void createNewCategory(CategoryRequest categoryRequest) {

        Category existCategory = categoryRepository.findByAlias(categoryRequest.alias())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category has not been found!"
                        )
                );

        if (categoryRequest.alias().equals(existCategory.getAlias())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Alias has already been exist"
            );
        }

        Category parentCategory = categoryRepository.findById(categoryRequest.parentCategoryID())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Parent Category has not been found"
                        )
                );

        // new category
//        Long parentID = categoryMapper.fromCategoryRequest(categoryRequest.parentCategoryID());
        Category newCategory = new Category();
        newCategory.setAlias(categoryRequest.alias());
        newCategory.setName(categoryRequest.name());
        newCategory.setIcon(categoryRequest.icon());
//        newCategory.setParentCategory(categoryRequest.parentCategoryID());
        newCategory.setParentCategory(parentCategory);
        newCategory.setDeleted(false);

        categoryRepository.save(newCategory);
    }

    @Override
    public List<CategoryParentResponse> findAllParentCategories() {

        List<Category> categories = categoryRepository.findAll()
                .stream()
                .filter(category -> category.getParentCategory() == null)
                .collect(Collectors.toList());

        return categoryMapper.toCategoryParentResponseList(categories);
    }

    @Override
    public CategoryResponse findCategoryByAlias(String alias) {
        Optional<Category> optionalCategory = categoryRepository.findByAlias(alias);
        Category category = optionalCategory.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category with alias " + alias + " has not been found!"));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> findAllCategoriesByPagination(int page, int size) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero");
        }

        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }

        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sortByName);

        Page<Category> accounts = categoryRepository.findAll(pageRequest);

        return accounts.map(categoryMapper::toCategoryResponse);
    }

    @Transactional
    @Override
    public BaseMessage disableCategoryByAlias(String alias) {

        if(!categoryRepository.existsByAlias(alias)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This category not found!");
        }

        categoryRepository.disableCategoryByAlias(alias);

        return new BaseMessage("Category has been disabled");
    }

}
