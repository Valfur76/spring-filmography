package com.spring.filmography.controller;

import com.spring.filmography.dto.OrderDTO;
import com.spring.filmography.model.Order;
import com.spring.filmography.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Tag(name = "Запрос на фильм", description = "Контроллер для работы с просмотром/покупкой фильма пользователям фильмотеки.")
public class OrderController extends GenericController<Order, OrderDTO> {
    public OrderController(OrderService orderService) {
        super(orderService);
    }

    @Operation(description = "Приобрести фильм", method = "rentFilm")
    @RequestMapping(value = "/rentFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> rentFilm(@RequestParam(value = "userId") Long userId,
                                           @RequestParam(value = "filmId") Long filmId,
                                           @RequestParam(value = "purchase") Boolean purchase,
                                           @RequestParam(value = "rentPeriod") Integer rentPeriod) {
        return ResponseEntity.status(HttpStatus.CREATED).body(((OrderService)service).rentFilm(userId, filmId, purchase, rentPeriod));
    }
}
