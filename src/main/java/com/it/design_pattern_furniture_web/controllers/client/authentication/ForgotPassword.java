package com.it.design_pattern_furniture_web.controllers.client.authentication;

import com.it.design_pattern_furniture_web.models.services.user.UserService;
import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ForgotPassword", value = "/forgot-password")
public class ForgotPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtils.forward(request, response, "views/client/forgot-password.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();

        boolean res = UserService.getInstance().forgotPassword(email);
        String status = "";
        if (!res) {
            out.println("error");
        } else {
            ServletUtils.redirect(response, request.getContextPath() + "/signin?forgot-password=true");
        }
    }
}
