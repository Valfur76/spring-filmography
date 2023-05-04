package com.spring.filmography.controller;

import com.spring.filmography.model.Director;
import com.spring.filmography.model.Film;
import com.spring.filmography.repository.DirectorRepository;
import com.spring.filmography.repository.FilmRepository;
import com.spring.filmography.repository.GenericRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RestController
@RequestMapping("/directors")
@Tag(name = "Режисеры", description = "Контроллекр для работы с режисерами фильмов из фильмотеки.")
public class DirectorController extends GenericController<Director> {
    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    public DirectorController(GenericRepository<Director> genericRepository,
                              DirectorRepository directorRepository,
                              FilmRepository filmRepository) {
        super(genericRepository);
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
    }

    @Operation(description = "Добавить фильм режисеру.")
    @RequestMapping(value = "/addFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Director> addFilm(@RequestParam(value = "filmId") Long filmId,
                                            @RequestParam(value = "directorId") Long directorId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("фильм не найден."));
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("режисер не найден."));
        director.getFilms().add(film);
        return ResponseEntity.status(HttpStatus.OK).body(directorRepository.save(director));
    }
}
