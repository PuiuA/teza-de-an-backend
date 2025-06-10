CREATE TABLE judo_moldova.competition (
      competition_id BIGSERIAL PRIMARY KEY,
      title TEXT NOT NULL,
      description TEXT NOT NULL,
      short_description TEXT NOT NULL,
      image BYTEA,
      information TEXT
);
CREATE TABLE judo_moldova.competition_link (
       competition_id BIGINT NOT NULL,
       link_id BIGINT NOT NULL,
       PRIMARY KEY (competition_id, link_id),
       FOREIGN KEY (competition_id) REFERENCES judo_moldova.competition(competition_id),
       FOREIGN KEY (link_id) REFERENCES judo_moldova.link(link_id)
);
