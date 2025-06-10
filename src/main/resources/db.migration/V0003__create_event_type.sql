CREATE TABLE judo_schema.event_type (
    event_type_id SERIAL PRIMARY KEY,
    event_name VARCHAR(255)
);
INSERT INTO judo_schema.event_type(event_name)VALUES ('COMPETIȚIE');
INSERT INTO judo_schema.event_type(event_name)VALUES ('CANTONAMENT');
INSERT INTO judo_schema.event_type(event_name)VALUES ('SEMINAR');
INSERT INTO judo_schema.event_type(event_name)VALUES ('REZULTAT');
INSERT INTO judo_schema.event_type(event_name)VALUES ('NOUTATE');