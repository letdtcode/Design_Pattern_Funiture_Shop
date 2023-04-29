package com.it.design_pattern_furniture_web.controllers.admin.product;


import com.it.design_pattern_furniture_web.models.services.product.ProductService;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductDetails", value = "/admin/product/detail")
public class ProductDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = StringUtils.toInt(request.getParameter("productId"));

        ProductViewModel product = ProductService.getInstance().retrieveProductById(productId);
        request.setAttribute("product", product);

        ServletUtils.forward(request, response, "/views/admin/product/product-detail.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
