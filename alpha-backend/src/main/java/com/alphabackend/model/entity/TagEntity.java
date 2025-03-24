package com.alphabackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tag", schema = "alpha_db_schema")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String label;

    @NotNull
    @Pattern(regexp = "^#(?:[0-9A-Fa-f]{3}|[0-9A-Fa-f]{6})$", message = "Le code couleur doit être au format hexadécimal (#RRGGBB)")
    private String color;

    @ManyToMany(mappedBy = "tagEntitySet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ImageIllustrationEntity> imageIllustrationEntitySet;

    @ManyToMany(mappedBy = "tagEntitySet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ImageProjectEntity> imageProjectEntitySet;

    @ManyToMany(mappedBy = "tagEntitySet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ProjectEntity> projectEntitySet;

}
