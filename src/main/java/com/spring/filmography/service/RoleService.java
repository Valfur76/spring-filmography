package com.spring.filmography.service;

import com.spring.filmography.dto.RoleDTO;
import com.spring.filmography.mapper.RoleMapper;
import com.spring.filmography.model.Role;
import com.spring.filmography.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<Role, RoleDTO>{
    public RoleService(RoleRepository repository, RoleMapper roleMapper) {
        super(repository, roleMapper);
    }
}
