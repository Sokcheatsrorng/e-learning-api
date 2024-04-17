package co.istad.elearningapi.features.category;


import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createNewCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryService.createNewCategory(categoryRequest);
    }
    @GetMapping("/{alias}")
    CategoryResponse findCategoryByAlias(@PathVariable String alias ){
        return categoryService.findCategoryByAlias(alias);
    }
    @GetMapping
    Page<CategoryResponse> findAllCategoriesByPagination(
            @RequestParam(required = false,defaultValue = "0")int page,
            @RequestParam(required = false,defaultValue = "25") int size
    ){
        return categoryService.findAllCategoriesByPagination(page,size);
    }
    @GetMapping("/parents")
    List<CategoryParentResponse> findAllParentCategories(){
        return categoryService.findAllParentCategories();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{alias}/disable")
    void updateByAlias(@PathVariable String alias) {
        categoryService.disableCategoryByAlias(alias);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{alias}")
    void updateCategoryByAlias(@PathVariable String alias, @Valid @RequestBody CategoryRequest request) {
        categoryService.updateCategoryByAlias(alias, request);
    }

}
