package com.it.design_pattern_furniture_web.controllers.admin.role;

import com.it.design_pattern_furniture_web.models.services.role.RoleService;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveRole", value = "/admin/role/delete")
public class RemoveRole extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roleId = StringUtils.toInt(request.getParameter("roleId"));

        boolean isSuccess = RoleService.getInstance().deleteRole(roleId);
        String error = "";
        if (!isSuccess) {
            error = "?error=true";
        }
        ServletUtils.redirect(response, request.getContextPath() + "/admin/roles" + error);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
