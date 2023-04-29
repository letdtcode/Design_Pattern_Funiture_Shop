package com.it.design_pattern_furniture_web.models.services.role;

import java.util.ArrayList;

import com.it.design_pattern_furniture_web.models.repositories.role.RoleRepository;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleViewModel;

public class RoleService implements IRoleService{
    private static RoleService instance = null;
    public static RoleService getInstance(){
        if(instance == null)
            instance = new RoleService();
        return instance;
    }
    @Override
    public int insertRole(RoleCreateRequest request) {
        return RoleRepository.getInstance().insert(request);
    }

    @Override
    public boolean updateRole(RoleUpdateRequest request) {
        return RoleRepository.getInstance().update(request);
    }

    @Override
    public boolean deleteRole(Integer roleId) {
        return RoleRepository.getInstance().delete(roleId);
    }
    @Override
    public RoleViewModel retrieveRoleById(Integer roleId) {
        return RoleRepository.getInstance().retrieveById(roleId);
    }

    @Override
    public ArrayList<RoleViewModel> retrieveAllRole(RoleGetPagingRequest request) {
        return RoleRepository.getInstance().retrieveAll(request);
    }
}
