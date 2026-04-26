ALTER TABLE judo_moldova.result
    ADD COLUMN IF NOT EXISTS year TEXT;

CREATE TABLE judo_moldova.athlete (
  athlete_id  BIGSERIAL PRIMARY KEY,
  name        VARCHAR(150) NOT NULL,
  club        VARCHAR(150),
  birth_year  INT,
  photo       BYTEA
);