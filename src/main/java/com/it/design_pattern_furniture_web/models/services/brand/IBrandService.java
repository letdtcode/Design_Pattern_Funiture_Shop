package com.it.design_pattern_furniture_web.models.services.brand;

import com.it.design_pattern_furniture_web.models.view_models.brands.BrandCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandViewModel;

import java.util.ArrayList;

public interface IBrandService {
    int insertBrand(BrandCreateRequest request);

    boolean updateBrand(BrandUpdateRequest request);

    boolean deleteBrand(Integer brandId);

    BrandViewModel retrieveBrandById(Integer brandId);

    ArrayList<BrandViewModel> retrieveAllBrand(BrandGetPagingRequest request);
}
