package com.it.design_pattern_furniture_web.models.services.category;

import com.it.design_pattern_furniture_web.models.repositories.category.CategoryRepository;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryService implements ICategoryService {
    private static CategoryService instance = null;

    public static CategoryService getInstance() {
        if (instance == null)
            instance = new CategoryService();
        return instance;
    }

    @Override
    public int insertCategory(CategoryCreateRequest request) {
        return CategoryRepository.getInstance().insert(request);
    }

    @Override
    public boolean updateCategory(CategoryUpdateRequest request) {
        return CategoryRepository.getInstance().update(request);
    }

    @Override
    public boolean deleteCategory(Integer categoryId) {
        return CategoryRepository.getInstance().delete(categoryId);
    }

    @Override
    public CategoryViewModel retrieveCategoryById(Integer categoryId) {
        return CategoryRepository.getInstance().retrieveById(categoryId);
    }

    @Override
    public ArrayList<CategoryViewModel> retrieveAllCategory(CategoryGetPagingRequest request) {
        return CategoryRepository.getInstance().retrieveAll(request);
    }


    public HashMap<Integer, String> getParentCategory() {
        return CategoryRepository.getInstance().getParentCategory();
    }
}
