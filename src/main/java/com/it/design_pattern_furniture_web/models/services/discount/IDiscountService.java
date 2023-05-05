package com.it.design_pattern_furniture_web.models.services.discount;

import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountViewModel;

import java.util.ArrayList;

public interface IDiscountService {
    int insertDiscount(DiscountCreateRequest request);

    boolean updateDiscount(DiscountUpdateRequest request);

    boolean deleteDiscount(Integer discountId);

    DiscountViewModel retrieveDiscountById(Integer discountId);

    ArrayList<DiscountViewModel> retrieveAllDiscount(DiscountGetPagingRequest request);

    DiscountViewModel getByDiscountCode(String discountCode);

    boolean updateQuantity(int discountId);
}
