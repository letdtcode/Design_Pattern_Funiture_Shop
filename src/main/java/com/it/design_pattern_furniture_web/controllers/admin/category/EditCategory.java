package com.it.design_pattern_furniture_web.controllers.admin.category;

import com.it.design_pattern_furniture_web.models.services.category.CategoryService;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "EditCategory", value = "/admin/category/edit")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class EditCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        String sub = request.getParameter("sub-categories");
        CategoryViewModel category = CategoryService.getInstance().retrieveCategoryById(StringUtils.toInt(categoryId));

        request.setAttribute("category", category);

        if (sub == null || sub.equals(""))
            ServletUtils.forward(request, response, "/admin/categories");
        else {
            ServletUtils.forward(request, response, "/admin/categories?sub-categories=true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Part categoryLogo = request.getPart("category-logo");
        String categoryName = request.getParameter("categoryName");
        String categoryId = request.getParameter("categoryId");
        String description = request.getParameter("description");
        String parentCategoryId = request.getParameter("parent-category");


        CategoryUpdateRequest req = new CategoryUpdateRequest();
        req.setCategoryId(StringUtils.toInt(categoryId));
        req.setDescription(description);
        req.setName(categoryName);
        req.setImage(categoryLogo);
        req.setStatus(StringUtils.toInt(request.getParameter("status")));
        if (parentCategoryId != null && !parentCategoryId.equals(""))
            req.setParentCategoryId(StringUtils.toInt(parentCategoryId));

        boolean isSuccess = CategoryService.getInstance().updateCategory(req);
        String error = "";
        if (!isSuccess) {
            error = "error=true";
        }
        if (parentCategoryId == null || parentCategoryId.equals(""))
            ServletUtils.redirect(response, request.getContextPath() + "/admin/categories?" + error);
        else
            ServletUtils.redirect(response, request.getContextPath() + "/admin/categories?sub-categories=true&" + error);
    }
}
