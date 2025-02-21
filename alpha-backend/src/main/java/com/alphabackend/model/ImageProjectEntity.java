package com.alphabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "image_project", schema = "alpha_db_schema")
public class ImageProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotNull
    private byte[] datas;

    @NotNull
    private Instant uploadDate;

    @ManyToOne
    @JoinColumn(name = "id_project")
    @NotNull
    private ProjectEntity projectEntity;

    @ManyToMany
    @JoinTable(
            name = "image_project_xxxx_tag",
            schema = "alpha_db_schema",
            joinColumns = @JoinColumn(name = "id_image"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private Set<TagEntity> tagEntitySet;
}
