package com.it.design_pattern_furniture_web.controllers.client.authentication;

import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "SignOut", value = "/signout")
public class SignOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie c = new Cookie("user", "");
        c.setMaxAge(0);
        response.addCookie(c);
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        ServletUtils.redirect(response, request.getContextPath() + "/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
