package com.spring.filmography.dto;

import com.spring.filmography.model.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class FilmDTO extends GenericDTO {
    private String filmTitle;
    private LocalDate premierDate;
    private String country;
    private Genre genre;
    private String description;
    private List<Long> directorsId;
    private List<Long> filmOrder;
}
