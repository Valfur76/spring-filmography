package com.spring.filmography.service;

import com.spring.filmography.dto.RoleDTO;
import com.spring.filmography.dto.UserDTO;
import com.spring.filmography.mapper.UserMapper;
import com.spring.filmography.model.User;
import com.spring.filmography.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, UserDTO> {
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
    }
    @Override
    public UserDTO create(UserDTO newObject) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        newObject.setRole(roleDTO);
        return super.create(newObject);
    }
}
