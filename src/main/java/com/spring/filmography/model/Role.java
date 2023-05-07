package com.spring.filmography.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@SequenceGenerator(name = "default_generator", sequenceName = "roles_sequence", allocationSize = 1)
public class Role extends GenericModel{

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
