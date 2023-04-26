package com.it.design_pattern_furniture_web.controllers.client.product;

import models.services.product.ProductService;
import models.view_models.products.ProductViewModel;
import utils.ServletUtils;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RetrieveProductDetail", value = "/product/details")
public class RetrieveProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int productId = StringUtils.toInt(request.getParameter("productId"));
        ProductViewModel product = ProductService.getInstance().retrieveProductById(productId);

        request.setAttribute("product", product);

        ServletUtils.forward(request, response, "/views/client/product-details.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
