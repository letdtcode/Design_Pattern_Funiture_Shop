package com.it.design_pattern_furniture_web.models.services.category;

import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICategoryService {
    int insertCategory(CategoryCreateRequest request);

    boolean updateCategory(CategoryUpdateRequest request);

    boolean deleteCategory(Integer categoryId);

    CategoryViewModel retrieveCategoryById(Integer categoryId);

    ArrayList<CategoryViewModel> retrieveAllCategory(CategoryGetPagingRequest request);

    HashMap<Integer, String> getParentCategory();
}
