package com.codegym.project.service.user;

import com.codegym.project.model.User;
import com.codegym.project.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);

}
