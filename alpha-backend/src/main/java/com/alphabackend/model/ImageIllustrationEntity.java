package com.alphabackend.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
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
}

