package com.it.design_pattern_furniture_web.controllers.admin.brand;

import com.it.design_pattern_furniture_web.models.services.brand.BrandService;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveBrand", value = "/admin/brand/delete")
public class RemoveBrand extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String brandId = request.getParameter("brandId");

        boolean isSuccess = BrandService.getInstance().deleteBrand(StringUtils.toInt(brandId));
        String error = "";
        if (!isSuccess) {
            error = "?error=true";
        }
        ServletUtils.redirect(response, request.getContextPath() + "/admin/brands" + error);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
