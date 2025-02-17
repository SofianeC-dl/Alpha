package com.alphabackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "image_project", schema = "alpha_db_schema")
public class ImageProjectEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotNull
    private byte[] datas;

    @ManyToOne
    @JoinColumn(name = "id_project")
    @NotNull
    private ProjectEntity projectEntity;

    public ImageProjectEntity() {};

    public ImageProjectEntity(Long id, byte[] description, ProjectEntity projectEntity) {
        this.id = id;
        this.datas = description;
        this.projectEntity = projectEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDatas() {
        return datas;
    }

    public void setDatas(byte[] datas) {
        this.datas = datas;
    }

    public ProjectEntity getProject() {
        return projectEntity;
    }

    public void setProject(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }
}
