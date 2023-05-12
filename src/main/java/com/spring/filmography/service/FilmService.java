package com.spring.filmography.service;

import com.spring.filmography.dto.FilmDTO;
import com.spring.filmography.mapper.FilmMapper;
import com.spring.filmography.model.Director;
import com.spring.filmography.model.Film;
import com.spring.filmography.model.Order;
import com.spring.filmography.repository.DirectorRepository;
import com.spring.filmography.repository.FilmRepository;
import com.spring.filmography.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FilmService extends GenericService<Film, FilmDTO> {
    private final DirectorRepository directorRepository;
    private final OrderRepository orderRepository;

    public FilmService(FilmRepository filmRepository,
                       FilmMapper filmMapper,
                       DirectorRepository directorRepository,
                       OrderRepository orderRepository) {
        super(filmRepository, filmMapper);
        this.directorRepository = directorRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public FilmDTO created(FilmDTO newObject) {
        return super.created(newObject);
    }

    public FilmDTO addDirector(final Long filmId, final Long directorId) {
        FilmDTO film = getOne(filmId);
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("Режиесер не найден."));
        film.getDirectorsId().add(director.getId());
        update(film);
        return film;
    }

    public List<FilmDTO> getAllFilmsByUserId(final Long userId) {
        Iterable<Order> ordersIterable = orderRepository.findAll();
        List<FilmDTO> films = new ArrayList<>();
        for (Order order : ordersIterable) {
            if (Objects.equals(userId, order.getUser().getId())) {
                FilmDTO filmDTO = getOne(order.getFilm().getId());
                films.add(filmDTO);
            }
        }
        return films;
    }
}
