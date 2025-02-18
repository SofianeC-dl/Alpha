CREATE TABLE IF NOT EXISTS alpha_db_schema.tag (
    id BIGSERIAL PRIMARY KEY,
    labelle VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS alpha_db_schema.image_illustration (
    id BIGSERIAL PRIMARY KEY,
    datas BYTEA NOT NULL
);

CREATE TABLE IF NOT EXISTS alpha_db_schema.project (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(1500) NOT NULL,
    id_image_illustration INTEGER NOT NULL,
    CONSTRAINT fk_image_illustration
        FOREIGN KEY (id_image_illustration)
            REFERENCES Image_illustration (id)
            ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS alpha_db_schema.image_project (
    id BIGSERIAL PRIMARY KEY,
    datas BYTEA NOT NULL,
    id_project INTEGER NOT NULL,
    CONSTRAINT fk_project
        FOREIGN KEY (id_project)
            REFERENCES Project (id)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS alpha_db_schema.project_tag_assoc (
    id_project BIGSERIAL NOT NULL,
    id_tag BIGSERIAL NOT NULL,
    PRIMARY KEY (id_project, id_tag),
    CONSTRAINT fk_project_tag_project
        FOREIGN KEY (id_project) REFERENCES Project(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_project_tag_tag
        FOREIGN KEY (id_tag) REFERENCES Tag(id)
        ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS alpha_db_schema.image_tag_assoc (
   id_image BIGSERIAL NOT NULL,
   id_tag BIGSERIAL NOT NULL,
   PRIMARY KEY (id_image, id_tag),
   CONSTRAINT fk_image_tag_image
       FOREIGN KEY (id_image) REFERENCES Image_project(id)
           ON DELETE CASCADE,
   CONSTRAINT fk_image_tag_tag
       FOREIGN KEY (id_tag) REFERENCES Tag(id)
           ON DELETE CASCADE
);