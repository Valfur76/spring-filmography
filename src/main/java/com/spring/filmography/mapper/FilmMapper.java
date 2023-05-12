package com.spring.filmography.mapper;

import com.spring.filmography.dto.FilmDTO;
import com.spring.filmography.model.Film;
import com.spring.filmography.model.GenericModel;
import com.spring.filmography.repository.DirectorRepository;
import com.spring.filmography.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class FilmMapper extends GenericMapper<Film, FilmDTO> {
    private final DirectorRepository directorRepository;
    private final OrderRepository orderRepository;

    public FilmMapper(ModelMapper modelMapper,
                      DirectorRepository directorRepository, OrderRepository orderRepository) {
        super(modelMapper, Film.class, FilmDTO.class);
        this.directorRepository = directorRepository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmDTO.class)
                .addMappings(mapping -> {
                    mapping.skip(FilmDTO::setDirectorsId);
                    mapping.skip(FilmDTO::setFilmOrderId);
                }).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(FilmDTO.class, Film.class)
                .addMappings(mapping -> mapping.skip(Film::setDirectors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmDTO source, Film destination) {
        if (!Objects.isNull(source.getDirectorsId())) {
            destination.setDirectors(directorRepository.findAllById(source.getDirectorsId()));
            destination.setOrders(orderRepository.findAllById(source.getFilmOrderId()));
        }
    }

    @Override
    protected void mapSpecificFields(Film source, FilmDTO destination) {
        destination.setDirectorsId(getIds(source));
        destination.setFilmOrderId(getOrderIds(source));
    }

    @Override
    protected List<Long> getIds(Film source) {
        return Objects.isNull(source) || Objects.isNull(source.getDirectors())
                ? Collections.emptyList()
                : source.getDirectors().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }

    protected List<Long> getOrderIds(Film source) {
        return Objects.isNull(source) || Objects.isNull(source.getOrders())
                ? Collections.emptyList()
                : source.getOrders().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
