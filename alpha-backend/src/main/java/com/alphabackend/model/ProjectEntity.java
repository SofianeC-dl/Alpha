package com.alphabackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
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

    public ProjectEntity() {}

    public ProjectEntity(Long id, String description, ImageIllustrationEntity imageIllustrationEntity) {
        this.id = id;
        this.description = description;
        this.imageIllustrationEntity = imageIllustrationEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageIllustrationEntity getImageIllustration() {
        return imageIllustrationEntity;
    }

    public void setImageIllustration(ImageIllustrationEntity idImageIllustrationEntity) {
        this.imageIllustrationEntity = idImageIllustrationEntity;
    }
}
