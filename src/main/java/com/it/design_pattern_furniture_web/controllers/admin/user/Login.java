package com.it.design_pattern_furniture_web.controllers.admin.user;

import com.it.design_pattern_furniture_web.common.user.UserUtils;
import com.it.design_pattern_furniture_web.models.services.user.UserService;
import com.it.design_pattern_furniture_web.models.view_models.user_roles.UserRoleViewModel;
import com.it.design_pattern_furniture_web.models.view_models.users.UserLoginRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.constants.USER_STATUS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/admin/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtils.forward(request, response, "/views/admin/signin/signin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        UserLoginRequest loginRequest = UserUtils.CreateLoginRequest(request);
        boolean isAdmin = false;
        boolean isBanned = false;
        boolean isLogin = false;
        boolean isUnConfirm = false;
        if (UserService.getInstance().login(loginRequest)) {
            isLogin = true;
            UserViewModel user = UserService.getInstance().getUserByUserName(loginRequest.getUsername());
            for (UserRoleViewModel role : user.getRoles()) {
                if (role.getRoleName().equalsIgnoreCase("admin")) {
                    Cookie c = new Cookie("admin", loginRequest.getUsername());
                    response.addCookie(c);
                    isAdmin = true;
                    if (user.getStatus() == USER_STATUS.IN_ACTIVE) {
                        isBanned = true;
                        break;
                    } else if (user.getStatus() == USER_STATUS.UN_CONFIRM) {
                        isUnConfirm = true;
                        break;
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("admin", user);
                    break;
                }
            }
        }
        if (!isLogin) {
            out.println("error");
        } else if (!isAdmin) {
            out.println("unauthorize");
        } else if (isBanned) {
            out.println("banned");
        } else if (isUnConfirm) {
            out.println("unconfirm");
        } else {
            ServletUtils.redirect(response, request.getContextPath() + "/admin/home");
        }
    }
}
