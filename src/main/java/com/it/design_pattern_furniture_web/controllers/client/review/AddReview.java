package com.it.design_pattern_furniture_web.controllers.client.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.design_pattern_furniture_web.models.services.review.ReviewService;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemCreateRequest;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.SessionUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;

@WebServlet(name = "AddReview", value = "/my-account/order/review/add")
public class AddReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int productId = StringUtils.toInt(request.getParameter("productId"));
        int userId = SessionUtils.getUserIdLogin(request);
        ReviewItemCreateRequest createReq = new ReviewItemCreateRequest();
        int reviewId = ReviewService.getInstance().getReviewIdByUserId(userId);

        createReq.setReviewId(reviewId);
        createReq.setRating(StringUtils.toInt(request.getParameter("rating")));
        createReq.setContent(request.getParameter("content"));
        createReq.setProductId(productId);
        createReq.setStatus(1);
        String error = "";
        int reviewItemId = ReviewService.getInstance().insertReviewItem(createReq);
        if(reviewItemId == -1)
            error = "&error=true";
        ServletUtils.redirect(response, request.getContextPath() + "/my-account/order/reviews?productId=" + productId + error);
    }
}
