CREATE TABLE judo_moldova.event_type (
    event_type_id SERIAL PRIMARY KEY,
    event_type VARCHAR(255)
);
INSERT INTO judo_moldova.event_type(event_type)VALUES ('COMPETIȚIE');
INSERT INTO judo_moldova.event_type(event_type)VALUES ('CANTONAMENT');
INSERT INTO judo_moldova.event_type(event_type)VALUES ('SEMINAR');
INSERT INTO judo_moldova.event_type(event_type)VALUES ('REZULTAT');
INSERT INTO judo_moldova.event_type(event_type)VALUES ('NOUTATE');