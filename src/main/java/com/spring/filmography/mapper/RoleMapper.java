package com.spring.filmography.mapper;

import com.spring.filmography.dto.RoleDTO;
import com.spring.filmography.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapper extends GenericMapper<Role, RoleDTO>{

    public RoleMapper(ModelMapper modelMapper) {
        super(modelMapper, Role.class, RoleDTO.class);
    }
    @Override
    protected void setupMapper() {
    }
    @Override
    protected void mapSpecificFields(RoleDTO source, Role destination) {
    }

    @Override
    protected void mapSpecificFields(Role source, RoleDTO destination) {
    }
    @Override
    protected List<Long> getIds(Role source) {
        return null;
//        throw new UnsupportedOperationException("Метод недоступен.");
    }
}
