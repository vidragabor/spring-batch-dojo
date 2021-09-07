CREATE TABLE IF NOT EXISTS "listing_status"
(
    id          BIGSERIAL NOT NULL
        CONSTRAINT listing_status_pk PRIMARY KEY,
    status_name TEXT      NOT NULL
);

CREATE TABLE IF NOT EXISTS "marketplace"
(
    id               BIGSERIAL NOT NULL
        CONSTRAINT marketplace_pk PRIMARY KEY,
    marketplace_name TEXT      NOT NULL
);