package com.it.design_pattern_furniture_web.controllers.client.wish;

import com.it.design_pattern_furniture_web.models.repositories.wish.WishRepository;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandViewModel;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.constants.BRAND_STATUS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetWishList", value = "/wish-list")
public class GetWishList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserViewModel user = (UserViewModel) session.getAttribute("user");
        if (user == null)
            return;
        int userId = user.getId();
        ArrayList<WishItemViewModel> wishItems = WishRepository.getInstance().retrieveWishListByUserId(userId);
        request.setAttribute("wishItems", wishItems);

        ArrayList<BrandViewModel> brands = models.repositories.brand.BrandRepository.getInstance().retrieveAll(new BrandGetPagingRequest());
        brands.removeIf(x -> x.getStatus() == BRAND_STATUS.IN_ACTIVE);
        request.setAttribute("brands", brands);

        ServletUtils.forward(request, response, "/views/client/wishlist.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
