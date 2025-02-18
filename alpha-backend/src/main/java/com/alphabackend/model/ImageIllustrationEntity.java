package com.alphabackend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "image_illustration", schema = "alpha_db_schema")
public class ImageIllustrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotNull
    @Column(columnDefinition = "BYTEA")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] datas;

    public ImageIllustrationEntity() {}

    public ImageIllustrationEntity(Long id, byte[] datas) {
        this.id = id;
        this.datas = datas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public byte[] getDatas() {
        return datas;
    }

    public void setDatas(byte[] datas) {
        this.datas = datas;
    }
}

