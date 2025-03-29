-- Créer le schéma s'il n'existe pas
CREATE SCHEMA IF NOT EXISTS alpha_db_schema;

CREATE TABLE IF NOT EXISTS alpha_db_schema.tag (
                                                   id BIGSERIAL PRIMARY KEY,
                                                   label VARCHAR(100) NOT NULL,
                                                   color VARCHAR(9) NOT NULL
    );

CREATE TABLE IF NOT EXISTS alpha_db_schema.image_illustration (
                                                                  id BIGSERIAL PRIMARY KEY,
                                                                  datas BYTEA NOT NULL,
                                                                  upload_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS alpha_db_schema.project (
                                                       id BIGSERIAL PRIMARY KEY,
                                                       description VARCHAR(1500) NOT NULL,
    upload_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                              id_image_illustration INTEGER NOT NULL,
                              CONSTRAINT fk_image_illustration
                              FOREIGN KEY (id_image_illustration)
    REFERENCES alpha_db_schema.image_illustration (id)
                          ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS alpha_db_schema.image_project (
                                                             id BIGSERIAL PRIMARY KEY,
                                                             datas BYTEA NOT NULL,
                                                             upload_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                                             id_project INTEGER NOT NULL,
                                                             CONSTRAINT fk_project
                                                             FOREIGN KEY (id_project)
    REFERENCES alpha_db_schema.project (id)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS alpha_db_schema.app_user (
                                                      id BIGSERIAL PRIMARY KEY,
                                                      username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS alpha_db_schema.project_xxxx_tag (
                                                                id_project BIGINT NOT NULL,
                                                                id_tag BIGINT NOT NULL,
                                                                PRIMARY KEY (id_project, id_tag),
    CONSTRAINT fk_project_tag_project
    FOREIGN KEY (id_project)
    REFERENCES alpha_db_schema.project(id)
    ON DELETE CASCADE,
    CONSTRAINT fk_project_tag_tag
    FOREIGN KEY (id_tag)
    REFERENCES alpha_db_schema.tag(id)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS alpha_db_schema.image_project_xxxx_tag (
                                                                      id_image BIGINT NOT NULL,
                                                                      id_tag BIGINT NOT NULL,
                                                                      PRIMARY KEY (id_image, id_tag),
    CONSTRAINT fk_image_project_tag_image
    FOREIGN KEY (id_image)
    REFERENCES alpha_db_schema.image_project(id)
    ON DELETE CASCADE,
    CONSTRAINT fk_image_project_tag_tag
    FOREIGN KEY (id_tag)
    REFERENCES alpha_db_schema.tag(id)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS alpha_db_schema.image_illustration_xxxx_tag (
                                                                           id_image BIGINT NOT NULL,
                                                                           id_tag BIGINT NOT NULL,
                                                                           PRIMARY KEY (id_image, id_tag),
    CONSTRAINT fk_image_illustration_xxxx_tag_image FOREIGN KEY (id_image)
    REFERENCES alpha_db_schema.image_illustration(id)
    ON DELETE CASCADE,
    CONSTRAINT fk_image_illustration_xxxx_tag_tag FOREIGN KEY (id_tag)
    REFERENCES alpha_db_schema.tag(id)
    ON DELETE CASCADE
    );
