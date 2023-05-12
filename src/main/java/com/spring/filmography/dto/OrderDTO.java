package com.spring.filmography.dto;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO extends GenericDTO {
    private Long id;
    private LocalDateTime rentDate;
    private Integer rentPeriod;
    private Boolean purchase;
    private Long filmId;
    private Long userId;
}
