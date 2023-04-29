package com.it.design_pattern_furniture_web.controllers.admin.discount;



import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddDiscount", value = "/admin/discount/add")
public class AddDiscount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        DiscountCreateRequest createReq = new DiscountCreateRequest();

        createReq.setDiscountCode(request.getParameter("discountCode"));
        createReq.setDiscountValue(StringUtils.toDouble(request.getParameter("discountValue")));
        createReq.setQuantity(StringUtils.toInt(request.getParameter("quantity")));
        createReq.setStartDate(DateUtils.stringToLocalDateTime(request.getParameter("startDate")));
        createReq.setEndDate(DateUtils.stringToLocalDateTime(request.getParameter("endDate")));
        createReq.setStatus(StringUtils.toInt(request.getParameter("status")));

        int discountId = DiscountService.getInstance().insertDiscount(createReq);
        String error = "";
        if(discountId < 1){
            error = "?error=true";
        }
        ServletUtils.redirect(response, request.getContextPath() + "/admin/discounts" + error);
    }
}
