package com.it.design_pattern_furniture_web.controllers.admin;

import models.services.order.OrderService;
import models.services.user.UserService;
import models.view_models.orders.OrderOverviewViewModel;
import models.view_models.orders.OrderViewModel;
import models.view_models.users.UserViewModel;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name = "AdminIndex", value = "/admin/home")
public class AdminIndex extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<UserViewModel> customers = UserService.getInstance().getTopUserByTotalOrder(10);
        long totalUsers = UserService.getInstance().getTotalUser();

        ArrayList<OrderViewModel> orders = OrderService.getInstance().getTopOrderSoon(10);
        long totalOrders = OrderService.getInstance().getTotalOrder();

        BigDecimal totalRevenue = OrderService.getInstance().getRevenue();

        OrderOverviewViewModel statistics = OrderService.getInstance().getOrderOverviewStatistics();

        request.setAttribute("totalUsers",totalUsers);
        request.setAttribute("totalOrders",totalOrders);
        request.setAttribute("totalRevenue",totalRevenue);
        request.setAttribute("customers", customers);
        request.setAttribute("orders", orders);
        request.setAttribute("statistics",statistics);

        ServletUtils.forward(request,response,"/views/admin/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
