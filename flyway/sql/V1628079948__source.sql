CREATE TABLE IF NOT EXISTS "source"
(
    id         BIGSERIAL NOT NULL
        CONSTRAINT source_pk PRIMARY KEY,
    first_name TEXT      NOT NULL,
    last_name  TEXT      NOT NULL,
    age        integer   NOT NULL
);