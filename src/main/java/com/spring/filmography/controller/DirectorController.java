package com.spring.filmography.controller;

import com.spring.filmography.dto.DirectorDTO;
import com.spring.filmography.model.Director;
import com.spring.filmography.service.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directors")
@Tag(name = "Режисеры", description = "Контроллекр для работы с режисерами фильмов из фильмотеки.")
public class DirectorController extends GenericController<Director, DirectorDTO> {
    public DirectorController(DirectorService directorService) {
        super(directorService);
    }

    @Operation(description = "Добавить фильм режисеру.")
    @RequestMapping(value = "/addFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDTO> addFilm(@RequestParam(value = "filmId") Long filmId,
                                               @RequestParam(value = "directorId") Long directorId) {
        return ResponseEntity.status(HttpStatus.OK).body(((DirectorService) service).addFilm(filmId, directorId));
    }
}
