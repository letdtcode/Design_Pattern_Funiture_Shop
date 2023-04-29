package com.it.design_pattern_furniture_web.controllers.client.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.design_pattern_furniture_web.models.services.review.ReviewService;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.SessionUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;

@WebServlet(name = "EditReview", value = "/my-account/order/review/edit")
public class EditReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reviewItemId = StringUtils.toInt(request.getParameter("reviewItemId"));
        ReviewItemViewModel productReview = ReviewService.getInstance().retrieveReviewItemById(reviewItemId);

        request.setAttribute("productReview",productReview);

        ServletUtils.forward(request, response, "/my-account/order/reviews?productId=" + productReview.getProductId());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int productId = StringUtils.toInt(request.getParameter("productId"));
        int reviewItemId = StringUtils.toInt(request.getParameter("reviewItemId"));

        int userId = SessionUtils.getUserIdLogin(request);
        ReviewItemUpdateRequest updateReq = new ReviewItemUpdateRequest();

        updateReq.setReviewItemId(reviewItemId);
        updateReq.setRating(StringUtils.toInt(request.getParameter("rating")));
        updateReq.setContent(request.getParameter("content"));

        boolean success = ReviewService.getInstance().updateReviewItem(updateReq);

        ServletUtils.redirect(response, request.getContextPath() + "/my-account/order/reviews?productId=" + productId);
    }
}
