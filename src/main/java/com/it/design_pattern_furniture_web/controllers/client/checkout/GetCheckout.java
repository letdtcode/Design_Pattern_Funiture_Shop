package com.it.design_pattern_furniture_web.controllers.client.checkout;

import com.it.design_pattern_furniture_web.models.services.cart.CartService;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.SessionUtils;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name = "GetCheckout", value = "/checkout")
public class GetCheckout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = SessionUtils.getUserIdLogin(request);
        if (userId == -1)
            return;
        ArrayList<CartItemViewModel> cartItems = CartService.getInstance().retrieveCartByUserId(userId);
        cartItems.removeIf(x -> x.getQuantity() == 0);
        request.setAttribute("cartItems", cartItems);
        BigDecimal totalItemPrice = CartService.getInstance().getTotalCartItemPriceByUserId(userId);
        BigDecimal shipping = BigDecimal.valueOf(0);
        BigDecimal discount = BigDecimal.valueOf(0);
        BigDecimal totalPrice;
        totalPrice = totalItemPrice.add(shipping);
        totalPrice = totalPrice.subtract(totalPrice.multiply(discount));

        request.setAttribute("totalItemPrice", totalItemPrice);
        request.setAttribute("shipping", shipping);
        request.setAttribute("discount", discount);
        request.setAttribute("totalPrice", totalPrice);
        ServletUtils.forward(request, response, "/views/client/checkout.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
