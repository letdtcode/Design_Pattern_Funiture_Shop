package com.it.design_pattern_furniture_web.controllers.client.authentication;

import com.it.design_pattern_furniture_web.models.services.google.GoogleModel;
import com.it.design_pattern_furniture_web.models.services.google.GoogleService;
import com.it.design_pattern_furniture_web.models.services.user.UserService;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.constants.USER_STATUS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GoogleLogin", value = "/login-google")
public class GoogleLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            ServletUtils.redirect(response, request.getContextPath() + "/signin?error=true");
        } else {
            String url = request.getRequestURL().toString();
            String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();
            String accessToken = GoogleService.getInstance().getToken(code, baseURL);
            GoogleModel googleUserInfo = GoogleService.getInstance().getGoogleAccountInfo(accessToken);
            UserViewModel user = UserService.getInstance().getUserByEmail(googleUserInfo.getEmail());
            if (user == null) {
                request.setAttribute("googleUser", googleUserInfo);
                ServletUtils.forward(request, response, "/register");
            } else {
                PrintWriter out = response.getWriter();
                if (user.getStatus() == USER_STATUS.IN_ACTIVE) {
                    ServletUtils.redirect(response, request.getContextPath() + "/signin?banned=true");
                } else if (user.getStatus() == USER_STATUS.UN_CONFIRM) {
                    ServletUtils.redirect(response, request.getContextPath() + "/signin?unconfirm=true");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    ServletUtils.redirect(response, request.getContextPath() + "/home");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
