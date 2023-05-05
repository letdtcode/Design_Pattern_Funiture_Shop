package com.it.design_pattern_furniture_web.models.repositories.wish;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemViewModel;

import java.util.ArrayList;

public interface IWishRepository extends IModifyEntity<WishItemCreateRequest, WishItemUpdateRequest, Integer>,
        IRetrieveEntity<WishItemViewModel, WishItemGetPagingRequest, Integer> {
    int getWishIdByUserId(int userId);

    ArrayList<WishItemViewModel> retrieveWishListByUserId(int userId);
}
