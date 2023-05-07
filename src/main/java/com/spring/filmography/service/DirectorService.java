package com.spring.filmography.service;

import com.spring.filmography.dto.DirectorDTO;
import com.spring.filmography.mapper.DirectorMapper;
import com.spring.filmography.model.Director;
import com.spring.filmography.model.Film;
import com.spring.filmography.repository.DirectorRepository;
import com.spring.filmography.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class DirectorService extends GenericService<Director, DirectorDTO> {
    private final FilmRepository filmRepository;

    public DirectorService(DirectorRepository directorRepository,
                           DirectorMapper directorMapper,
                           FilmRepository filmRepository) {
        super(directorRepository, directorMapper);
        this.filmRepository = filmRepository;
    }

    public DirectorDTO addFilm(final Long filmId, final Long directorId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм не найден."));
        DirectorDTO director = getOne(directorId);
        director.getFilmsId().add(film.getId());
        update(director);
        return director;
    }
}
