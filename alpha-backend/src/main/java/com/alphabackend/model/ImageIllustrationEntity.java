package com.alphabackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Set;

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
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private byte[] datas;

    @Column(name = "upload_date", nullable = false)
    private Instant uploadDate;

    @ManyToMany
    @JoinTable(
            name = "image_illustration_xxxx_tag",
            schema = "alpha_db_schema",
            joinColumns = @JoinColumn(name = "id_image"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private Set<TagEntity> tagEntitySet;
}

