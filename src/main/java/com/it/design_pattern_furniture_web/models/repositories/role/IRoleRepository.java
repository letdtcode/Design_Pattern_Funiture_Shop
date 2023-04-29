package com.it.design_pattern_furniture_web.models.repositories.role;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleViewModel;

public interface IRoleRepository  extends IModifyEntity<RoleCreateRequest, RoleUpdateRequest, Integer>,
        IRetrieveEntity<RoleViewModel, RoleGetPagingRequest, Integer> {
}
