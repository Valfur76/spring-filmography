package com.spring.filmography.controller;

import com.spring.filmography.dto.UserDTO;
import com.spring.filmography.model.User;
import com.spring.filmography.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями фильмотеки.")
public class UserController extends GenericController<User, UserDTO>{
    public UserController(UserService userService) {
        super(userService);
    }
}
