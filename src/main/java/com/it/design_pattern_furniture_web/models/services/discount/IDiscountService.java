package com.it.design_pattern_furniture_web.models.services.discount;

import models.view_models.discounts.DiscountCreateRequest;
import models.view_models.discounts.DiscountGetPagingRequest;
import models.view_models.discounts.DiscountUpdateRequest;
import models.view_models.discounts.DiscountViewModel;

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
