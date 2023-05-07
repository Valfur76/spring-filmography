package com.spring.filmography.controller;

import com.spring.filmography.dto.RoleDTO;
import com.spring.filmography.model.Role;
import com.spring.filmography.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@Tag(name = "Роли пользователей", description = "Контроллер для работы с ролями пользователей фильмотеки.")
public class RoleController extends GenericController<Role, RoleDTO>{

    protected RoleController(RoleService roleService) {
        super(roleService);
    }
}
