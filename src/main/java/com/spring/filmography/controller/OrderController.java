package com.spring.filmography.controller;

import com.spring.filmography.model.Order;
import com.spring.filmography.repository.GenericRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Tag(name = "Запрос на фильм", description = "Контроллер для работы с просмотром/покупкой фильма пользователям фидьмотеки.")
public class OrderController extends GenericController<Order>{
    public OrderController(GenericRepository<Order> genericRepository) {
        super(genericRepository);
    }
}
