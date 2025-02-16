CREATE TABLE IF NOT EXISTS Tag (
    id SERIAL PRIMARY KEY,
    labelle VARCHAR(100) NOT NULL,
    tag_type VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Image_illustration (
    id SERIAL PRIMARY KEY,
    datas TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Project (
    id SERIAL PRIMARY KEY,
    description VARCHAR(1500) NOT NULL,
    id_image_illustration INTEGER NOT NULL,
    CONSTRAINT fk_image_illustration
        FOREIGN KEY (id_image_illustration)
            REFERENCES Image_illustration (id)
            ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS Image_project (
    id SERIAL PRIMARY KEY,
    datas TEXT NOT NULL,
    id_project INTEGER NOT NULL,
    CONSTRAINT fk_project
        FOREIGN KEY (id_project)
            REFERENCES Project (id)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Project_Tag_Assoc (
    id_project INT NOT NULL,
    id_tag INT NOT NULL,
    PRIMARY KEY (id_project, id_tag),
    CONSTRAINT fk_project_tag_project
        FOREIGN KEY (id_project) REFERENCES Project(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_project_tag_tag
        FOREIGN KEY (id_tag) REFERENCES Tag(id)
        ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS Image_Tag_Assoc (
   id_image INT NOT NULL,
   id_tag INT NOT NULL,
   PRIMARY KEY (id_image, id_tag),
   CONSTRAINT fk_image_tag_image
       FOREIGN KEY (id_image) REFERENCES Image_project(id)
           ON DELETE CASCADE,
   CONSTRAINT fk_image_tag_tag
       FOREIGN KEY (id_tag) REFERENCES Tag(id)
           ON DELETE CASCADE
);