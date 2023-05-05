package com.it.design_pattern_furniture_web.controllers.admin.user;

import com.it.design_pattern_furniture_web.common.user.UserUtils;
import com.it.design_pattern_furniture_web.models.services.user.UserService;
import com.it.design_pattern_furniture_web.models.view_models.users.UserUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "EditUser", value = "/admin/user/edit")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserUpdateRequest reqUpdate = UserUtils.CreateUserUpdateRequest(request);

        boolean isSuccess = UserService.getInstance().updateUser(reqUpdate);
        String error = "";
        if (!isSuccess) {
            error = "&error=true";
        } else {
            UserViewModel user = UserService.getInstance().getUserByUserName(request.getParameter("username"));
            HttpSession session = request.getSession();
            UserViewModel currUser = (UserViewModel) session.getAttribute("admin");
            if (Objects.equals(currUser.getUsername(), user.getUsername())) {
                session.setAttribute("admin", user);
            }
        }
        ServletUtils.redirect(response, request.getContextPath() + "/admin/user/detail?userId=" + reqUpdate.getUserId() + error);
    }
}
