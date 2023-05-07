package com.spring.filmography.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class DirectorDTO extends GenericDTO {
    private String directorFIO;
    private String position;
    List<Long> filmsId;
}
