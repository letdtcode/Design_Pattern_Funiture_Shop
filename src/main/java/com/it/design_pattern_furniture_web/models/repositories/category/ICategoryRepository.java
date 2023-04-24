package com.it.design_pattern_furniture_web.models.repositories.category;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryViewModel;

import java.util.HashMap;

public interface ICategoryRepository extends IModifyEntity<CategoryCreateRequest, CategoryUpdateRequest, Integer>,
        IRetrieveEntity<CategoryViewModel, CategoryGetPagingRequest, Integer> {
    HashMap<Integer, String> getParentCategory();
}
