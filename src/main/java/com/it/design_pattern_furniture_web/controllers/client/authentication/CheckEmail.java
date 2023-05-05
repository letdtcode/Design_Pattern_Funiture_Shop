package com.it.design_pattern_furniture_web.controllers.client.authentication;

import com.it.design_pattern_furniture_web.models.services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckEmail", value = "/email-check")
public class CheckEmail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        boolean contain = UserService.getInstance().checkEmail(email);
        PrintWriter out = response.getWriter();
        if (!contain) {
            out.println("non-exist");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
