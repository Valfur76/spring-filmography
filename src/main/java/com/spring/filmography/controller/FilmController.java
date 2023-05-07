package com.spring.filmography.controller;

import com.spring.filmography.dto.FilmDTO;
import com.spring.filmography.model.Film;
import com.spring.filmography.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильмы", description = "Контроллекр для работы с фильмами фильмотеки.")
public class FilmController extends GenericController<Film, FilmDTO> {
    public FilmController(FilmService filmService) {
        super(filmService);
    }
    @Operation(description = "Добавить режисера фильму.")
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDTO> addDirector(@RequestParam(value = "directorId") Long directorId,
                                            @RequestParam(value = "filmId") Long filmId) {
        return ResponseEntity.status(HttpStatus.OK).body(((FilmService) service).addDirector(filmId, directorId));
    }

    @Operation(description = "Получить все фильмы у пользователя", method = "getAllFilmsByUserId")
    @RequestMapping(value = "/getAllFilmsByUserId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmDTO>> getAllFilmsByUserId(@RequestParam(value = "userId") Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(((FilmService) service).getAllFilmsByUserId(userId));
    }
}
