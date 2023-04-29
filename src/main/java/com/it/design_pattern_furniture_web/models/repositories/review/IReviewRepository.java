package com.it.design_pattern_furniture_web.models.repositories.review;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemViewModel;

import java.util.ArrayList;

public interface IReviewRepository extends IModifyEntity<ReviewItemCreateRequest, ReviewItemUpdateRequest, Integer>,
        IRetrieveEntity<ReviewItemViewModel, ReviewItemGetPagingRequest, Integer> {
    boolean ChangeStatus(int reviewItemId);

    ArrayList<ReviewItemViewModel> retrieveByProductId(Integer productId);

    ArrayList<ReviewItemViewModel> retrieveByUserId(Integer userId);

    ArrayList<ReviewItemViewModel> retrieveUserReviewByProductId(Integer userId, Integer productId);

    int getReviewIdByUserId(int userId);
}
