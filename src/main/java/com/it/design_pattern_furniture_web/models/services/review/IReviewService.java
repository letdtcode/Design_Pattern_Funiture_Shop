package com.it.design_pattern_furniture_web.models.services.review;

import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemViewModel;

import java.util.ArrayList;

public interface IReviewService {
    int insertReviewItem(ReviewItemCreateRequest request);

    boolean updateReviewItem(ReviewItemUpdateRequest request);

    boolean deleteReviewItem(Integer reviewItemId);

    ReviewItemViewModel retrieveReviewItemById(Integer reviewItemId);

    ArrayList<ReviewItemViewModel> retrieveAllReviewItem(ReviewItemGetPagingRequest request);

    boolean ChangeReviewItemStatus(int reviewItemId);

    ArrayList<ReviewItemViewModel> retrieveReviewItemByProductId(Integer productId);

    ArrayList<ReviewItemViewModel> retrieveReviewItemByUserId(Integer userId);

    ArrayList<ReviewItemViewModel> retrieveUserReviewByProductId(Integer userId, Integer productId);

    int getReviewIdByUserId(int userId);
}
