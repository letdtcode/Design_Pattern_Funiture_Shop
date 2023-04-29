package com.it.design_pattern_furniture_web.models.repositories.user;

import java.util.ArrayList;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.user_roles.UserRoleViewModel;
import com.it.design_pattern_furniture_web.models.view_models.users.UserCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserLoginRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;

public interface IUserRepository extends IModifyEntity<UserCreateRequest, UserUpdateRequest, Integer>,
        IRetrieveEntity<UserViewModel, UserGetPagingRequest, Integer> {

    boolean checkUsername(String username);
    boolean checkEmail(String email);
    boolean checkPhone(String phone);
    boolean checkPassword(int userId, String password);
    boolean login(UserLoginRequest request);
    ArrayList<UserViewModel> getTopUserByTotalOrder(int top);
    UserViewModel getUserByUserName(String username);
    UserViewModel getUserByEmail(String email);
    long getTotalUser();
    ArrayList<UserRoleViewModel> getUserRoleByUserId(int userId);

    boolean forgotPassword(String email);
}
