package co.istad.elearningapi.features.category;


import co.istad.elearningapi.features.category.dto.CategoryCreateRequest;
import co.istad.elearningapi.features.category.dto.CategoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PutMapping("/{alias}/disable")
    void isDisableCategory ( @PathVariable String alias ) {
        categoryService.isDisableCategory(alias);
    }
    @PostMapping
    void createCategory ( @RequestBody CategoryCreateRequest category ) {
        categoryService.createCategory(category);
    }

    @PutMapping("/{alias}")
    void updateCategory ( @PathVariable String alias, @RequestBody CategoryUpdateRequest category ) {
        categoryService.updateCategoryByAlias(alias, category);
    }
}
