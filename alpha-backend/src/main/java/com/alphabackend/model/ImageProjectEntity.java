package com.alphabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @ManyToOne
    @JoinColumn(name = "id_project")
    @NotNull
    private ProjectEntity projectEntity;
}
