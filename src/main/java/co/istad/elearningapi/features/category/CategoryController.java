package co.istad.elearningapi.features.category;


import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    CategoryResponse findAllCategoryByAlias(@PathVariable String alias ){
        return categoryService.findAllCategoryByAlias(alias);
    }
    @GetMapping
    Page<CategoryResponse> findAllCategoriesByPagination(
            @RequestParam(required = false,defaultValue = "0")int page,
            @RequestParam(required = false,defaultValue = "25") int size
    ){
        return categoryService.findAllCategoriesByPagination(page,size);
    }
    @GetMapping("/{parentId}/subcategories")
    public ResponseEntity<List<Category>> getSubCategories(@PathVariable Long parentId) {
        try {
            List<Category> subCategories = categoryService.findAllSubCategories(parentId);
            return ResponseEntity.ok(subCategories);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/parents")
    List<CategoryParentResponse> findAllParentCategories(){
        return categoryService.findAllParentCategories();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{alias}")
    void updateByAlias(@PathVariable String alias) {
        categoryService.disableCategoryByAlias(alias);
    }



}
