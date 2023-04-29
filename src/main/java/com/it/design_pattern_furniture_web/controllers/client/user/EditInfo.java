package com.it.design_pattern_furniture_web.controllers.client.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.it.design_pattern_furniture_web.common.user.UserUtils;
import com.it.design_pattern_furniture_web.models.services.user.UserService;
import com.it.design_pattern_furniture_web.models.view_models.users.UserUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.constants.USER_STATUS;

@WebServlet(name = "EditInfo", value = "/my-account/edit")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class EditInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserUpdateRequest reqUpdate = UserUtils.CreateUserUpdateRequest(request);
        reqUpdate.setStatus(USER_STATUS.ACTIVE);
        boolean isSuccess = UserService.getInstance().updateUser(reqUpdate);
        if(!isSuccess){
            request.setAttribute("error", "error");
        }else{
            UserViewModel user = UserService.getInstance().getUserByUserName(request.getParameter("username"));
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            request.setAttribute("user", user);
        }

        ServletUtils.redirect(response, request.getContextPath() + "/my-account?info=true");
    }
}
