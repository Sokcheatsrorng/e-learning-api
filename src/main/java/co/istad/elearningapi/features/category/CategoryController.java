package co.istad.elearningapi.features.category;


import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewCategory(@Valid @RequestBody CategoryRequest request){
        categoryService.createNewCategory(request);
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
