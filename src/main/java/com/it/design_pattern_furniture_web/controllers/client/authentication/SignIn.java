package com.it.design_pattern_furniture_web.controllers.client.authentication;

import com.it.design_pattern_furniture_web.common.user.UserUtils;
import com.it.design_pattern_furniture_web.models.services.google.GoogleService;
import com.it.design_pattern_furniture_web.models.services.user.UserService;
import com.it.design_pattern_furniture_web.models.view_models.users.UserLoginRequest;
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

@WebServlet(name = "SignIn", value = "/signin")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserViewModel user = (UserViewModel) session.getAttribute("user");
        if (user != null) {
            ServletUtils.redirect(response, request.getContextPath() + "/home");
        } else {
            String url = request.getRequestURL().toString();
            String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();
            String scope = "profile%20email";
            String googleLoginLink = "https://accounts.google.com/o/oauth2/auth?scope=" + scope + "&redirect_uri=" + baseURL + "/login-google&response_type=code&client_id=" + GoogleService.GOOGLE_CLIENT_ID + "&approval_prompt=force";
            request.setAttribute("urlGoogleLogin", googleLoginLink);
            ServletUtils.forward(request, response, "/views/client/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        UserLoginRequest loginRequest = UserUtils.CreateLoginRequest(request);

        if (UserService.getInstance().login(loginRequest)) {
            UserViewModel user = UserService.getInstance().getUserByUserName(loginRequest.getUsername());
            if (user.getStatus() == USER_STATUS.IN_ACTIVE) {
                out.println("banned".trim());

            } else if (user.getStatus() == USER_STATUS.UN_CONFIRM) {
                out.println("unconfirm".trim());
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                ServletUtils.redirect(response, request.getContextPath() + "/home");
            }
        } else {
            out.println("error".trim());
        }

    }
}
