package com.it.design_pattern_furniture_web.controllers.client.review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.design_pattern_furniture_web.models.services.product.ProductService;
import com.it.design_pattern_furniture_web.models.services.review.ReviewService;
import com.it.design_pattern_furniture_web.models.view_models.review_items.ReviewItemViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.SessionUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;

@WebServlet(name = "GetReviewsByProduct", value = "/my-account/order/reviews")
public class GetReviewsByProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = StringUtils.toInt(request.getParameter("productId"));
        int userId = SessionUtils.getUserIdLogin(request);
        ArrayList<ReviewItemViewModel> reviewItems = ReviewService.getInstance().retrieveUserReviewByProductId(userId, productId);
        request.setAttribute("reviewItems",reviewItems);
        request.setAttribute("product", ProductService.getInstance().retrieveProductById(productId));
        ServletUtils.forward(request,response,"/views/client/review.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
