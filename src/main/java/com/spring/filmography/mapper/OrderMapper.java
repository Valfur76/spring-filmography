package com.spring.filmography.mapper;

import com.spring.filmography.dto.OrderDTO;
import com.spring.filmography.model.Order;
import com.spring.filmography.repository.FilmRepository;
import com.spring.filmography.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

@Component
public class OrderMapper extends GenericMapper<Order, OrderDTO> {
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    public OrderMapper(ModelMapper modelMapper,
                       UserRepository userRepository,
                       FilmRepository filmRepository) {
        super(modelMapper, Order.class, OrderDTO.class);
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        super.modelMapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(mapping -> mapping.skip(OrderDTO::setUserId))
                .addMappings(mapping -> mapping.skip(OrderDTO::setFilmId))
                .setPostConverter(toDTOConverter());
        super.modelMapper.createTypeMap(OrderDTO.class, Order.class)
                .addMappings(mapping -> mapping.skip(Order::setUser))
                .addMappings(mapping -> mapping.skip(Order::setFilm))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(OrderDTO source, Order destination) {
        destination.setUser(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователь не найден.")));
        destination.setFilm(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильм не найден.")));
    }

    @Override
    protected void mapSpecificFields(Order source, OrderDTO destination) {
        destination.setUserId(source.getUser().getId());
        destination.setFilmId(source.getFilm().getId());
    }

    @Override
    protected List<Long> getIds(Order entity) {
        throw new UnsupportedOperationException("Метод недоступен.");
    }
}
