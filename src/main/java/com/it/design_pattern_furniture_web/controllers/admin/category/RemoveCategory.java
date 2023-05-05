package com.it.design_pattern_furniture_web.controllers.admin.category;

import com.it.design_pattern_furniture_web.models.services.category.CategoryService;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveCategory", value = "/admin/category/delete")
public class RemoveCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        String sub = request.getParameter("sub-categories");

        boolean isSuccess = CategoryService.getInstance().deleteCategory(StringUtils.toInt(categoryId));
        String error = "";
        if (!isSuccess) {
            error = "error=true";
        }
        if (sub == null || sub.equals("")) {
            error = "?" + error;
            ServletUtils.redirect(response, request.getContextPath() + "/admin/categories" + error);
        } else {
            error = "&" + error;
            ServletUtils.redirect(response, request.getContextPath() + "/admin/categories?sub-categories=true" + error);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
