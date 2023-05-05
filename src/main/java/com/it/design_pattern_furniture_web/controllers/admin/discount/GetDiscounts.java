package com.it.design_pattern_furniture_web.controllers.admin.discount;

import com.it.design_pattern_furniture_web.models.services.discount.DiscountService;
import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.discounts.DiscountViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetDiscounts", value = "/admin/discounts")
public class GetDiscounts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiscountGetPagingRequest req = new DiscountGetPagingRequest();
        ArrayList<DiscountViewModel> discounts = DiscountService.getInstance().retrieveAllDiscount(req);

        request.setAttribute("discounts", discounts);
        String error = request.getParameter("error");
        if (error != null && !error.equals("")) {
            request.setAttribute("error", error);
        }
        ServletUtils.forward(request, response, "/views/admin/discount/discount.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
