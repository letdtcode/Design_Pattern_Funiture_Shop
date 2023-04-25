package com.it.design_pattern_furniture_web.controllers.admin.role;

import models.services.role.RoleService;
import models.view_models.roles.RoleCreateRequest;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddRole", value = "/admin/role/add")
public class AddRole extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        RoleCreateRequest createReq = new RoleCreateRequest();

        createReq.setRoleName(request.getParameter("roleName"));

        int roleId = RoleService.getInstance().insertRole(createReq);
        String error = "";
        if(roleId < 1){
            error = "?error=true";
        }
        ServletUtils.redirect(response, request.getContextPath() + "/admin/roles" + error);
    }
}
