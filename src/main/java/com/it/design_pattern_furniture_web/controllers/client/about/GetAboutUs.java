package com.it.design_pattern_furniture_web.controllers.client.about;

import com.it.design_pattern_furniture_web.models.services.brand.BrandService;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.constants.BRAND_STATUS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetAboutUs", value = "/about")
public class GetAboutUs extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BrandGetPagingRequest req = new BrandGetPagingRequest();
        ArrayList<BrandViewModel> brands = BrandService.getInstance().retrieveAllBrand(req);
        brands.removeIf(x -> x.getStatus() == BRAND_STATUS.IN_ACTIVE);
        request.setAttribute("brands", brands);
        ServletUtils.forward(request, response, "/views/client/about.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
