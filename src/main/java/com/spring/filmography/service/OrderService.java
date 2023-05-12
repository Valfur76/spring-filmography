package com.spring.filmography.service;

import com.spring.filmography.dto.OrderDTO;
import com.spring.filmography.mapper.OrderMapper;
import com.spring.filmography.model.Order;
import com.spring.filmography.repository.OrderRepository;
import org.springframework.stereotype.Service;
@Service
public class OrderService extends GenericService<Order, OrderDTO> {
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        super(orderRepository, orderMapper);
    }
}
