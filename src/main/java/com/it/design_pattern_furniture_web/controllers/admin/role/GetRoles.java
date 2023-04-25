package com.it.design_pattern_furniture_web.controllers.admin.role;

import models.services.role.RoleService;
import models.view_models.roles.RoleGetPagingRequest;
import models.view_models.roles.RoleViewModel;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetRoles", value = "/admin/roles")
public class GetRoles extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleGetPagingRequest req = new RoleGetPagingRequest();
        ArrayList<RoleViewModel> roles = RoleService.getInstance().retrieveAllRole(req);

        request.setAttribute("roles",roles);
        String error = request.getParameter("error");
        if(error != null && !error.equals("")){
            request.setAttribute("error",error);
        }
        ServletUtils.forward(request,response,"/views/admin/role/role.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
