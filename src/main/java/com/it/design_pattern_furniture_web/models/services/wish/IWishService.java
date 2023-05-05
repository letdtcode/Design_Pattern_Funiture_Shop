package com.it.design_pattern_furniture_web.models.services.wish;

import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemViewModel;

import java.util.ArrayList;

public interface IWishService {
    int insertWishItem(WishItemCreateRequest request);

    boolean updateWishItem(WishItemUpdateRequest request);

    boolean deleteWishItem(Integer wishItemId);

    WishItemViewModel retrieveWishItemById(Integer wishItemId);

    ArrayList<WishItemViewModel> retrieveAllWishItem(WishItemGetPagingRequest request);

    int getWishIdByUserId(int userId);

    ArrayList<WishItemViewModel> retrieveWishListByUserId(int userId);
}
