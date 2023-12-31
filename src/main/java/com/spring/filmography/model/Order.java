package com.spring.filmography.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "orders_sequence", allocationSize = 1)
public class Order extends GenericModel {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDER_FILMS"))
    private Film film;

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;

    @Column(name = "rent_period", nullable = false)
    private Integer rentPeriod;

    @Column(name = "purchase", nullable = false)
    private Boolean purchase;
}
