package com.codegym.project.service.role;

import com.codegym.project.model.Role;
import com.codegym.project.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);

}
