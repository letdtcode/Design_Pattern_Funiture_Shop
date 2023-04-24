package com.it.design_pattern_furniture_web.controllers.admin.product;

import com.google.gson.Gson;
import models.services.product.ProductService;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RemoveProductImage", value = "/admin/product/images/delete")
public class RemoveProductImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productImageId = StringUtils.toInt(request.getParameter("productImageId"));
        boolean isSuccess = ProductService.getInstance().deleteImage(productImageId);

        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(isSuccess));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
