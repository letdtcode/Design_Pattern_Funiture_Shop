package com.it.design_pattern_furniture_web.controllers.admin.user;

import com.it.design_pattern_furniture_web.models.services.role.RoleService;
import com.it.design_pattern_furniture_web.models.services.user.UserService;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleViewModel;
import com.it.design_pattern_furniture_web.models.view_models.users.UserGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetUsers", value = "/admin/users")
public class GetUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserGetPagingRequest reqUser = new UserGetPagingRequest();
        ArrayList<UserViewModel> users = UserService.getInstance().retrieveAllUser(reqUser);

        request.setAttribute("users", users);

        RoleGetPagingRequest reqRole = new RoleGetPagingRequest();
        ArrayList<RoleViewModel> roles = RoleService.getInstance().retrieveAllRole(reqRole);
        request.setAttribute("roles", roles);
        String error = request.getParameter("error");
        if (error != null && !error.equals("")) {
            request.setAttribute("error", error);
        }

        ServletUtils.forward(request, response, "/views/admin/user/list-user.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
