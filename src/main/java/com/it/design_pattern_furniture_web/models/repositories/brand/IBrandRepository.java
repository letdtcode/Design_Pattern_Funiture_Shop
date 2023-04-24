package com.it.design_pattern_furniture_web.models.repositories.brand;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandViewModel;

public interface IBrandRepository extends IModifyEntity<BrandCreateRequest, BrandUpdateRequest, Integer>,
        IRetrieveEntity<BrandViewModel, BrandGetPagingRequest, Integer> {
}
