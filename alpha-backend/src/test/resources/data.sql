-- Création d'exemples de tags
INSERT INTO alpha_db_schema.tag (id, label, color) VALUES (3,'Tag A', '#ddddddFF');
INSERT INTO alpha_db_schema.tag (id, label, color) VALUES (4, 'Tag B', '#ffffffFF');

-- Insertion d'une image d'illustration
-- Ici, la valeur X'48656C6C6F' correspond à la chaîne "Hello" en hexadécimal
INSERT INTO alpha_db_schema.image_illustration (id, datas, upload_date)
VALUES (2, X'48656C6C6F', '2025-02-26T22:00:00Z');

-- Insertion d'un projet
-- On utilise l'image d'illustration dont l'id est 1 (première insertion)
INSERT INTO alpha_db_schema.project (id, description, id_image_illustration, update_date, upload_date)
VALUES (3, 'Description du projet de test', 2, '2025-02-26T22:00:00Z', '2025-02-26T22:00:00Z');

-- Insertion d'une image de projet
-- On utilise le projet avec id 1
-- La valeur X'576F726C64' correspond à la chaîne "World"
INSERT INTO alpha_db_schema.image_project (id, datas, id_project, upload_date)
VALUES (3, X'576F726C64', 3, '2025-02-26T22:00:00Z');

-- Insertion d'un utilisateur
-- Le nom "user1", un mot de passe factice et un rôle
INSERT INTO alpha_db_schema.app_user (username, password, role)
VALUES ( 'Username Admin Test', '$2a$10$nH68bNEOV1vo4Hqaktop1.13gx90YIxzAWiZNQeaPquHG8XH893vy', 'ADMIN');

INSERT INTO alpha_db_schema.app_user (username, password, role)
VALUES ( 'Username User Test', 'Password User Test', 'USER');

-- Insertion dans la table de jointure Project <-> Tag
-- Associe le projet (id=1) aux tags (id=1 et id=2)
INSERT INTO alpha_db_schema.project_xxxx_tag (id_project, id_tag)
VALUES (3, 3);
INSERT INTO alpha_db_schema.project_xxxx_tag (id_project, id_tag)
VALUES (3, 4);

-- Insertion dans la table de jointure ImageProject <-> Tag
-- Associe l'image de projet (id=1) au tag (id=1)
INSERT INTO alpha_db_schema.image_project_xxxx_tag (id_image, id_tag)
VALUES (3, 3);

-- Insertion dans la table de jointure ImageIllustration <-> Tag
-- Associe l'image d'illustration (id=1) au tag (id=2)
INSERT INTO alpha_db_schema.image_illustration_xxxx_tag (id_image, id_tag)
VALUES (2, 4);
