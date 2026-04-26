ALTER TABLE judo_moldova.category_result
    DROP COLUMN IF EXISTS competitors;

-- Adaugam coloanele lipsă în athlete (creat deja în V14)
ALTER TABLE judo_moldova.athlete
    ADD COLUMN IF NOT EXISTS weight_kg DECIMAL(5,2),
    ADD COLUMN IF NOT EXISTS belt VARCHAR(50);

CREATE TABLE judo_moldova.athlete_result (
     athlete_result_id   BIGSERIAL PRIMARY KEY,
     athlete_id          BIGINT NOT NULL REFERENCES judo_moldova.athlete(athlete_id),
     category_result_id  BIGINT NOT NULL REFERENCES judo_moldova.category_result(category_result_id),
     place               INT NOT NULL
);