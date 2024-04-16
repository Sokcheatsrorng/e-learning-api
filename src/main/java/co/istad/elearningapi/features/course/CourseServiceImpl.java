package co.istad.elearningapi.features.category;


import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryCreateRequest;
import co.istad.elearningapi.features.category.dto.CategoryResponse;
import co.istad.elearningapi.features.category.dto.CategoryUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceimpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Value("${MEDIA_BASE_URI}")
    private String mediaBaseUri;

    @Transactional
    @Override
    public BaseMessage isDisableCategory(String alias) {
        if(!categoryRepository.existsByAlias(alias)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This category not found!");
        }

        categoryRepository.disableCategoryByAlias(alias);
        return new BaseMessage("Category has been disabled");
    }

    @Override
    public void createCategory(CategoryCreateRequest categoryCreateRequest) {

    }

    @Override
    public void updateCategoryByAlias(String alias, CategoryUpdateRequest updateRequest){

        Category category = categoryRepository.findByAlias(alias).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This category not found!"));

        category.setAlias(updateRequest.alias());
        category.setName(updateRequest.name());
        category.setIcon(mediaBaseUri + "IMAGE/" + updateRequest.icon());
        categoryRepository.save(category);
    }


}