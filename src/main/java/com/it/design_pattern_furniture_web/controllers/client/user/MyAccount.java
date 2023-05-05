package com.it.design_pattern_furniture_web.controllers.client.user;

import com.it.design_pattern_furniture_web.models.services.order.OrderService;
import com.it.design_pattern_furniture_web.models.view_models.orders.OrderViewModel;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "MyAccount", value = "/my-account")
public class MyAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserViewModel user = (UserViewModel) session.getAttribute("user");
        String info = request.getParameter("info");
        String url = "";
        if (info != null) {
            request.setAttribute("user", user);
            url = "/views/client/my-account-info.jsp";
        } else {
            ArrayList<OrderViewModel> orders = OrderService.getInstance().retrieveOrderByUserId(user.getId());

            request.setAttribute("orders", orders);
            url = "/views/client/my-account-order.jsp";
        }
        ServletUtils.forward(request, response, url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
