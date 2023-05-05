package com.it.design_pattern_furniture_web.controllers.admin.user;

import com.it.design_pattern_furniture_web.models.services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CheckAddUser", value = "/users/check-add")
public class CheckAddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<String> exists = new ArrayList<>();
        if (UserService.getInstance().checkUsername(request.getParameter("username"))) {
            exists.add("user".trim());
        }
        if (UserService.getInstance().checkEmail(request.getParameter("email"))) {
            exists.add("email".trim());
        }
        if (UserService.getInstance().checkPhone(request.getParameter("phone"))) {
            exists.add("phone".trim());
        }
        PrintWriter out = response.getWriter();
        out.println(exists);
    }
}
