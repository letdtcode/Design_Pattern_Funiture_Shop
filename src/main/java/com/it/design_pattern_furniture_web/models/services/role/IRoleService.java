package com.it.design_pattern_furniture_web.models.services.role;



import java.util.ArrayList;

import com.it.design_pattern_furniture_web.models.view_models.roles.RoleCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleViewModel;

public interface IRoleService {
    int insertRole(RoleCreateRequest request);
    boolean updateRole(RoleUpdateRequest request);
    boolean deleteRole(Integer roleId);
    RoleViewModel retrieveRoleById(Integer roleId);
    ArrayList<RoleViewModel> retrieveAllRole(RoleGetPagingRequest request);

}
