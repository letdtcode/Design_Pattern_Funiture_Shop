package com.it.design_pattern_furniture_web.controllers.client.wish_list;

import com.it.design_pattern_furniture_web.models.services.wish.WishService;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.models.view_models.wish_items.WishItemCreateRequest;
import com.it.design_pattern_furniture_web.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddWish", value = "/add-wish")
public class AddWish extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        int productId = StringUtils.toInt(request.getParameter("productId"));

        HttpSession session = request.getSession();
        UserViewModel user = (UserViewModel) session.getAttribute("user");
        if (user == null)
            return;
        int userId = user.getId();
        int wishId = WishService.getInstance().getWishIdByUserId(userId);

        WishItemCreateRequest createReq = new WishItemCreateRequest();
        createReq.setProductId(productId);
        createReq.setStatus(1);
        createReq.setWishId(wishId);

        int count = WishService.getInstance().insertWishItem(createReq);

        if (count <= 0) {
            out.println("error");
        } else {
            user.setTotalWishListItem(user.getTotalWishListItem() + 1);
            session.setAttribute("user", user);
            out.println(user.getTotalWishListItem() + "success");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
