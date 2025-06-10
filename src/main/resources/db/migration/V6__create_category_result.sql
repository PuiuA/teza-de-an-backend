CREATE TABLE judo_moldova.category_result (
     category_result_id BIGSERIAL PRIMARY KEY,
     category_id BIGINT NOT NULL,
     result_id BIGINT NOT NULL,
     competitors TEXT,
     CONSTRAINT fk_category FOREIGN KEY (category_id)REFERENCES judo_moldova.category(category_id),
     CONSTRAINT fk_result FOREIGN KEY (result_id) REFERENCES judo_moldova.result(result_id)
);
