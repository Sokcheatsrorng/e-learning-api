package co.istad.elearningapi.features.category;

import co.istad.elearningapi.base.BaseMessage;
import co.istad.elearningapi.features.category.dto.CategoryParentResponse;
import co.istad.elearningapi.features.category.dto.CategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    @Override
    public void createNewCategory(CategoryRequest request) {

    }

    @Override
    public List<CategoryParentResponse> findAllParentCategories() {
        return null;
    }

    @Override
    public BaseMessage isDisableCategory(String alias) {
        return null;
    }

}
