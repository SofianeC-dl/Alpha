package com.alphabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "project", schema = "alpha_db_schema")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 1500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_image_illustration")
    @NotNull
    private ImageIllustrationEntity imageIllustrationEntity;
}
