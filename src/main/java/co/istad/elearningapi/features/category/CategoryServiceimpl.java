package co.istad.elearningapi.features.category;


import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryCreateRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceimpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public void isDisableCategory(String alias) {
        if(!categoryRepository.existsByAlias(alias)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This category not found!");
        }
        categoryRepository.existsByAlias(alias);

    }

    @Override
    public void createCategory(CategoryCreateRequest categoryCreateRequest) {
        if (categoryCreateRequest.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This category is deleted!");
        }
        Category category = new Category();
        category.setAlias(categoryCreateRequest.alias());
        category.setName(categoryCreateRequest.name());
        category.setDeleted(false);
        categoryRepository.save(category);

    }
}
