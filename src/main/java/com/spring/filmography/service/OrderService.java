package com.spring.filmography.service;

import com.spring.filmography.dto.OrderDTO;
import com.spring.filmography.mapper.OrderMapper;
import com.spring.filmography.model.Film;
import com.spring.filmography.model.Order;
import com.spring.filmography.model.User;
import com.spring.filmography.repository.FilmRepository;
import com.spring.filmography.repository.OrderRepository;
import com.spring.filmography.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;

@Service
public class OrderService extends GenericService<Order, OrderDTO> {
    private final FilmRepository filmRepository;

    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, FilmRepository filmRepository, UserRepository userRepository) {
        super(orderRepository, orderMapper);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }
    public OrderDTO rentFilm(final Long userId, final Long filmId, final Boolean purchase,final Integer rentPeriod) {
        OrderDTO orderDTO = new OrderDTO();
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользовательне найден."));
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм не найден."));
        orderDTO.setCreatedWhen(LocalDateTime.now());
        orderDTO.setCreatedBy("UserId : " + user.getId().toString());
        orderDTO.setFilmId(film.getId());
        orderDTO.setUserId(user.getId());
        orderDTO.setPurchase(purchase);
        orderDTO.setRentDate(LocalDateTime.now().plusMonths(rentPeriod)) ;
        orderDTO.setRentPeriod(rentPeriod);
        return mapper.toDTO(repository.save(mapper.toEntity(orderDTO)));
    }
}
