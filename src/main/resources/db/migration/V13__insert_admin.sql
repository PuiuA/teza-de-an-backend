DROP TABLE IF EXISTS judo_moldova.admin;

CREATE TABLE judo_moldova.admin (
    admin_id  BIGSERIAL PRIMARY KEY,
    username  VARCHAR(100) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(50)  NOT NULL DEFAULT 'ADMIN'
);


CREATE TABLE judo_moldova.refresh_token (
    token_id    BIGSERIAL PRIMARY KEY,
    token       VARCHAR(255) NOT NULL UNIQUE,
    admin_id    BIGINT NOT NULL REFERENCES judo_moldova.admin(admin_id),
    expiry_date TIMESTAMPTZ NOT NULL,
    revoked     BOOLEAN NOT NULL DEFAULT FALSE
);

