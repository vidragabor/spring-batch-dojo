CREATE TABLE IF NOT EXISTS "user"
(
    id         BIGSERIAL NOT NULL
        CONSTRAINT user_pk PRIMARY KEY,
    first_name TEXT      NOT NULL,
    last_name  TEXT      NOT NULL,
    age        integer   NOT NULL
);