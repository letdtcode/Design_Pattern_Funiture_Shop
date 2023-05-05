package com.it.design_pattern_furniture_web.controllers.client.cart;

import com.it.design_pattern_furniture_web.models.services.cart.CartService;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemUpdateRequest;
import com.it.design_pattern_furniture_web.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateItem", value = "/cart/update")
public class UpdateItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int cartItemId = StringUtils.toInt(request.getParameter("cartItemId"));
        int quantity = StringUtils.toInt(request.getParameter("quantity"));

        CartItemUpdateRequest updateReq = new CartItemUpdateRequest();
        updateReq.setCartItemId(cartItemId);
        updateReq.setQuantity(quantity);

        boolean success = CartService.getInstance().updateCartItem(updateReq);
        if (success) {
            out.println("success");
        } else {
            int q = CartService.getInstance().canUpdateQuantity(cartItemId, quantity);
            if (q != -1)
                out.println(q + "-over");
            else
                out.println("error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
