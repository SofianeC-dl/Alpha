package com.alphabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @ManyToMany(mappedBy = "tagEntitySet")
    private Set<ImageIllustrationEntity> imageIllustrationEntitySet;

    @ManyToMany(mappedBy = "tagEntitySet")
    private Set<ImageProjectEntity> imageProjectEntitySet;

    @ManyToMany(mappedBy = "tagEntitySet")
    private Set<ProjectEntity> projectEntitySet;

}
