package com.it.design_pattern_furniture_web.controllers.client.cart;

import com.it.design_pattern_furniture_web.models.services.cart.CartService;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RemoveItem", value = "/cart/remove")
public class RemoveItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int cartItemId = StringUtils.toInt(request.getParameter("cartItemId"));
        boolean success = CartService.getInstance().deleteCartItem(cartItemId);
        if (!success) {
            out.println("error");
        } else {
            HttpSession session = request.getSession();
            UserViewModel user = (UserViewModel) session.getAttribute("user");
            if (user == null) {
                out.println("error");
                return;
            }
            user.setTotalCartItem(user.getTotalCartItem() - 1);
            session.setAttribute("user", user);
            //ServletUtils.redirect(response, request.getContextPath() + "/wish-list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
