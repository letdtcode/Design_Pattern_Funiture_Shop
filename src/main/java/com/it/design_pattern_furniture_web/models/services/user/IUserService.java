package com.it.design_pattern_furniture_web.models.services.user;

import java.util.ArrayList;

import com.it.design_pattern_furniture_web.models.view_models.user_roles.UserRoleViewModel;
import com.it.design_pattern_furniture_web.models.view_models.users.UserCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserLoginRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;

public interface IUserService {
    int insertUser(UserCreateRequest request);
    boolean updateUser(UserUpdateRequest request);
    boolean deleteUser(Integer userId);
    UserViewModel retrieveUserById(Integer userId);
    ArrayList<UserViewModel> retrieveAllUser(UserGetPagingRequest request);
    boolean checkUsername(String username);
    boolean checkEmail(String email);
    boolean checkPhone(String phone);
    boolean checkPassword(int userId, String password);
    boolean login(UserLoginRequest request);
    UserViewModel getUserByUserName(String username);
    UserViewModel getUserByEmail(String email);
    ArrayList<UserViewModel> getTopUserByTotalOrder(int top);
    long getTotalUser();
    ArrayList<UserRoleViewModel> getUserRoleByUserId(int userId);
    boolean forgotPassword(String email);
}
