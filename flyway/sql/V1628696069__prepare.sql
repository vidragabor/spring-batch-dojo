CREATE TABLE IF NOT EXISTS "prepare"
(
    id         BIGSERIAL NOT NULL
        CONSTRAINT prepare_pk PRIMARY KEY,
    first_name TEXT      NOT NULL,
    last_name  TEXT      NOT NULL,
    age        integer   NOT NULL
);

INSERT INTO "prepare" (id, first_name, last_name, age)
VALUES (1, 'Elek', 'Teszt', 30),
       (2, 'Réka', 'Nyom', 21),
       (3, 'Zita', 'Para', 32),
       (4, 'Nóra', 'Patta', 43),
       (5, 'Zoltán', 'Pár', 51),
       (6, 'Simon', 'Pop', 12),
       (7, 'Elek', 'Remek', 35),
       (8, 'Elek', 'Beviz', 8),
       (9, 'Ella', 'Szalmon', 11),
       (10, 'Kolos', 'Techno', 83),
       (11, 'Antal', 'Trab', 92),
       (12, 'Viola', 'Ultra', 43),
       (13, 'Erika', 'Am', 12),
       (14, 'Ilus', 'Bac', 23),
       (15, 'Ella', 'Citad', 31),
       (16, 'Emma', 'Dil', 72),
       (17, 'Lenke', 'Eszte', 81),
       (18, 'Elek', 'Feles', 68),
       (19, 'Imre', 'Füty', 34),
       (20, 'Áron', 'Git', 99),
       (21, 'Mónika', 'Har', 1),
       (22, 'Réka', 'Heu', 52),
       (23, 'Jenő', 'Hü', 44),
       (24, 'Ica', 'Kukor', 22),
       (25, 'Pál', 'Kala', 59),
       (26, 'Nóra', 'Külö', 79),
       (27, 'Ödön', 'Körm', 33),
       (28, 'Ede', 'Kér', 54),
       (29, 'Anna', 'Major', 82),
       (30, 'Győző', 'Meg', 26);