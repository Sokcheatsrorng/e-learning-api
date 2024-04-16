package co.istad.elearningapi.features.category;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.domain.Category;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.resource.transaction.backend.jta.internal.JtaTransactionAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;




@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Value("${MEDIA_BASE_URI}")
    private String mediaBaseUri;

    @Override
    public void updateCategoryByAlias(String alias, CategoryRequest updateRequest){

        Category category = categoryRepository.findByAlias(alias).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This category not found!"));

        category.setAlias(updateRequest.alias());
        category.setName(updateRequest.name());
        category.setIcon(mediaBaseUri + "IMAGE/" + updateRequest.icon());
        categoryRepository.save(category);
    }

    @Override
    public void createNewCategory(CategoryRequest request) {

    }

    @Override
    public List<CategoryParentResponse> findAllParentCategories() {
        return null;
    }

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
