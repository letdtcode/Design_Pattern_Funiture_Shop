package com.it.design_pattern_furniture_web.controllers.client.wish_list;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetWishList", value = "/wish-list")
public class GetWishList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserViewModel user = (UserViewModel) session.getAttribute("user");
        if(user == null)
            return;
        int userId = user.getId();
        ArrayList<WishItemViewModel> wishItems = WishService.getInstance().retrieveWishListByUserId(userId);
        request.setAttribute("wishItems", wishItems);

        ArrayList<BrandViewModel> brands = BrandService.getInstance().retrieveAllBrand(new BrandGetPagingRequest());
        brands.removeIf(x -> x.getStatus() == BRAND_STATUS.IN_ACTIVE);
        request.setAttribute("brands", brands);

        ServletUtils.forward(request,response,"/views/client/wishlist.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
