CREATE TABLE tb_motorcycles (

    id NUMBER PRIMARY KEY,
    license VARCHAR2(50) NOT NULL,
    chassis VARCHAR2(100),
    engine VARCHAR2(100),
    status_color VARCHAR2(50) CHECK (status_color IN ('YELLOW','BLUE','ORANGE','RED','GRAY','DARK_GREEN','PINK','LIGHT_GREEN')),
    id_model NUMBER,
    id_device NUMBER UNIQUE,
    CONSTRAINT fk_motorcycles_models FOREIGN KEY (id_model) REFERENCES tb_models(id)

);
