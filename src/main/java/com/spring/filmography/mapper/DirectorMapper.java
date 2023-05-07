package com.spring.filmography.mapper;

import com.spring.filmography.dto.DirectorDTO;
import com.spring.filmography.model.Director;
import com.spring.filmography.model.GenericModel;
import com.spring.filmography.repository.FilmRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DirectorMapper extends GenericMapper<Director, DirectorDTO> {
    private final FilmRepository filmRepository;

    public DirectorMapper(ModelMapper modelMapper,
                          FilmRepository filmRepository) {
        super(modelMapper, Director.class, DirectorDTO.class);
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Director.class, DirectorDTO.class)
                .addMappings(mapping -> mapping.skip(DirectorDTO::setFilmsId)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(DirectorDTO.class, Director.class)
                .addMappings(mapping -> mapping.skip(Director::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(DirectorDTO source, Director destination) {
        if (!Objects.isNull(source.getFilmsId())) {
            destination.setFilms(filmRepository.findAllById(source.getFilmsId()));
        } else {
            destination.setFilms(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Director source, DirectorDTO destination) {
        destination.setFilmsId(getIds(source));
    }

    @Override
    protected List<Long> getIds(Director source) {
        return Objects.isNull(source) || Objects.isNull(source.getFilms())
                ? Collections.emptyList()
                : source.getFilms().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
