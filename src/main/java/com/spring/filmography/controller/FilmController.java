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
@RequestMapping("/films")
@Tag(name = "Фильмы", description = "Контроллекр для работы с фильмами фильмотеки.")
public class FilmController extends GenericController<Film>{
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

    public FilmController(GenericRepository<Film> genericRepository,
                          FilmRepository filmRepository,
                          DirectorRepository directorRepository) {
        super(genericRepository);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }
    @Operation(description = "Добавить режисера фильму.")
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> addFilms(@RequestParam(value = "directorId") Long directorId,
                                            @RequestParam(value = "filmId") Long filmId){
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("фильм не найден."));
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("режисер не найден."));
        film.getDirectors().add(director);
        return ResponseEntity.status(HttpStatus.OK).body(filmRepository.save(film));
    }
}
