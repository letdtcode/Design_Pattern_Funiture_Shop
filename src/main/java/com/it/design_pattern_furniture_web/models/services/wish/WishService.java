package com.it.design_pattern_furniture_web.models.services.wish;

import com.it.design_pattern_furniture_web.models.repositories.wish.WishRepository;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemViewModel;

import java.util.ArrayList;

public class WishService implements IWishService {
    private static WishService instance = null;

    public static WishService getInstance() {
        if (instance == null)
            instance = new WishService();
        return instance;
    }

    @Override
    public int insertWishItem(WishItemCreateRequest request) {
        return WishRepository.getInstance().insert(request);
    }

    @Override
    public boolean updateWishItem(WishItemUpdateRequest request) {
        return WishRepository.getInstance().update(request);
    }

    @Override
    public boolean deleteWishItem(Integer wishItemId) {
        return WishRepository.getInstance().delete(wishItemId);
    }

    @Override
    public WishItemViewModel retrieveWishItemById(Integer wishItemId) {
        return WishRepository.getInstance().retrieveById(wishItemId);
    }

    @Override
    public ArrayList<WishItemViewModel> retrieveAllWishItem(WishItemGetPagingRequest request) {
        return WishRepository.getInstance().retrieveAll(request);
    }

    @Override
    public int getWishIdByUserId(int userId) {
        return WishRepository.getInstance().getWishIdByUserId(userId);
    }

    @Override
    public ArrayList<WishItemViewModel> retrieveWishListByUserId(int userId) {
        return WishRepository.getInstance().retrieveWishListByUserId(userId);
    }
}
