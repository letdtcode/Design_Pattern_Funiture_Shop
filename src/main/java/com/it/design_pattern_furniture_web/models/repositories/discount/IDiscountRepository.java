package com.it.design_pattern_furniture_web.models.repositories.discount;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountViewModel;

public interface IDiscountRepository extends IModifyEntity<DiscountCreateRequest, DiscountUpdateRequest, Integer>,
        IRetrieveEntity<DiscountViewModel, DiscountGetPagingRequest, Integer> {
    DiscountViewModel getByDiscountCode(String discountCode);

    boolean updateQuantity(int discountId);
}
