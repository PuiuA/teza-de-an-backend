CREATE TABLE judo_moldova.news (
       news_id BIGSERIAL PRIMARY KEY,
       title TEXT NOT NULL,
       description TEXT NOT NULL,
       short_description TEXT NOT NULL,
       published TIMESTAMP NOT NULL,
       event_type_id BIGINT NOT NULL,
       image BYTEA,
       author TEXT,
       information TEXT,
       FOREIGN KEY (event_type_id) REFERENCES judo_moldova.event_type(event_type_id)
);
CREATE TABLE judo_moldova.news_link (
        news_id BIGINT NOT NULL,
        link_id BIGINT NOT NULL,
        PRIMARY KEY (news_id, link_id),
        FOREIGN KEY (news_id) REFERENCES judo_moldova.news(news_id),
        FOREIGN KEY (link_id) REFERENCES judo_moldova.link(link_id)
);
