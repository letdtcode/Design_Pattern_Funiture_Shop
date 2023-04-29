package com.it.design_pattern_furniture_web.models.services.brand;

import java.util.ArrayList;

import com.it.design_pattern_furniture_web.models.repositories.brand.BrandRepository;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandViewModel;

public class BrandService implements IBrandService{
    private static BrandService instance = null;

    public static BrandService getInstance(){
        if (instance == null)
            instance = new BrandService();
        return instance;
    }
    @Override
    public int insertBrand(BrandCreateRequest request) {
        return BrandRepository.getInstance().insert(request);
    }

    @Override
    public boolean updateBrand(BrandUpdateRequest request) {
        return BrandRepository.getInstance().update(request);
    }

    @Override
    public boolean deleteBrand(Integer brandId) {
        return BrandRepository.getInstance().delete(brandId);
    }

    @Override
    public BrandViewModel retrieveBrandById(Integer brandId) {
        return BrandRepository.getInstance().retrieveById(brandId);
    }

    @Override
    public ArrayList<BrandViewModel> retrieveAllBrand(BrandGetPagingRequest request) {
        return BrandRepository.getInstance().retrieveAll(request);
    }
}
