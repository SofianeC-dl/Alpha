package com.alphabackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

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

    @Column(name = "upload_date", nullable = false)
    private Instant uploadDate;

    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_image_illustration")
    @NotNull
    private ImageIllustrationEntity imageIllustrationEntity;

    @ManyToMany
    @JoinTable(
            name = "project_xxxx_tag",
            schema = "alpha_db_schema",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private Set<TagEntity> tagEntitySet;
}
