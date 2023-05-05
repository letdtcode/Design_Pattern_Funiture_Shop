package com.it.design_pattern_furniture_web.controllers.client.cart;

import com.it.design_pattern_furniture_web.models.services.brand.BrandService;
import com.it.design_pattern_furniture_web.models.services.cart.CartService;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandViewModel;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemViewModel;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.constants.BRAND_STATUS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name = "GetCart", value = "/cart/items")
public class GetCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserViewModel user = (UserViewModel) session.getAttribute("user");
        if (user == null)
            return;
        int userId = user.getId();
        ArrayList<CartItemViewModel> cartItems = CartService.getInstance().retrieveCartByUserId(userId);
        request.setAttribute("cartItems", cartItems);

        ArrayList<BrandViewModel> brands = BrandService.getInstance().retrieveAllBrand(new BrandGetPagingRequest());
        brands.removeIf(x -> x.getStatus() == BRAND_STATUS.IN_ACTIVE);
        request.setAttribute("brands", brands);
        BigDecimal total = CartService.getInstance().getTotalCartItemPriceByUserId(userId);
        request.setAttribute("total", total);
        ServletUtils.forward(request, response, "/views/client/cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
