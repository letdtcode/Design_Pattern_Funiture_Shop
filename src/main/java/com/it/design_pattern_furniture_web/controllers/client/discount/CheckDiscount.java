package com.it.design_pattern_furniture_web.controllers.client.discount;

import com.google.gson.Gson;
import models.services.discount.DiscountService;
import models.view_models.discounts.DiscountViewModel;
import utils.DateUtils;
import utils.constants.DISCOUNT_STATUS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckDiscount", value = "/discount/apply")
public class CheckDiscount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String discountCode = request.getParameter("discountCode");
        DiscountViewModel discount = DiscountService.getInstance().getByDiscountCode(discountCode);
        if(discount == null)
            out.println("error");
        else if(discount.getStatus() == DISCOUNT_STATUS.SUSPENDED){
            out.println("suspended");
        }
        else if (DateUtils.stringToLocalDateTime(discount.getStartDate().replace(" ", "T")).isAfter(DateUtils.dateTimeNow()) ||
                DateUtils.stringToLocalDateTime(discount.getEndDate().replace(" ", "T")).isBefore(DateUtils.dateTimeNow())){
            out.println("expired");
        }
        else if(discount.getQuantity() == 0){
            out.println("out");
        }
        else {
            out.println(new Gson().toJson(discount));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
