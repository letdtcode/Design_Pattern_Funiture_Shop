package com.it.design_pattern_furniture_web.controllers.admin.category;

import com.it.design_pattern_furniture_web.models.services.category.CategoryService;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "GetCategories", value = "/admin/categories")
public class GetCategories extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryGetPagingRequest req = new CategoryGetPagingRequest();
        String sub = request.getParameter("sub-categories");

        req.setTypeSort("brandName");
        ArrayList<CategoryViewModel> categories = CategoryService.getInstance().retrieveAllCategory(req);
        String error = request.getParameter("error");
        if (error != null && !error.equals("")) {
            request.setAttribute("error", error);
        }
        if (sub == null || sub.equals("")) {
            categories.removeIf(s -> s.getParentCategoryId() > 0);
            request.setAttribute("categories", categories);
            ServletUtils.forward(request, response, "/views/admin/category/main-category.jsp");
        } else {
            categories.removeIf(s -> s.getParentCategoryId() == 0);
            request.setAttribute("categories", categories);
            HashMap<Integer, String> parentCategories = CategoryService.getInstance().getParentCategory();
            request.setAttribute("parentCategories", parentCategories);
            ServletUtils.forward(request, response, "/views/admin/category/sub-category.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
