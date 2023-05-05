package com.it.design_pattern_furniture_web.controllers.admin.review;

import com.it.design_pattern_furniture_web.models.services.review.ReviewService;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetReviews", value = "/admin/reviews")
public class GetReviews extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewItemGetPagingRequest req = new ReviewItemGetPagingRequest();

        ArrayList<ReviewItemViewModel> reviews = ReviewService.getInstance().retrieveAllReviewItem(req);

        request.setAttribute("reviews", reviews);
        String error = request.getParameter("error");
        if (error != null && !error.equals("")) {
            request.setAttribute("error", error);
        }
        ServletUtils.forward(request, response, "/views/admin/review/review.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
