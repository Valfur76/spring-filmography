package com.spring.filmography.mapper;

import com.spring.filmography.dto.UserDTO;
import com.spring.filmography.model.GenericModel;
import com.spring.filmography.model.User;
import com.spring.filmography.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDTO>{
    private final OrderRepository orderRepository;
    public UserMapper(ModelMapper modelMapper,
                      OrderRepository orderRepository) {
        super(modelMapper, User.class, UserDTO.class);
        this.orderRepository = orderRepository;
    }
    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(mapping -> mapping.skip(UserDTO::setUserOrder)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(mapping -> mapping.skip(User::setOrders)).setPostConverter(toEntityConverter());
    }
    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {
        if (!Objects.isNull(source.getUserOrder())){
            destination.setOrders(orderRepository.findAllById(source.getUserOrder()));
        }else {
            destination.setOrders(Collections.emptyList());
        }
    }
    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {
        destination.setUserOrder(getIds(source));
    }
    @Override
    protected List<Long> getIds(User source) {
        return Objects.isNull(source) || Objects.isNull(source.getOrders())
                ? Collections.emptyList()
                : source.getOrders().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
