CREATE TABLE tb_models (

    id NUMBER PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    id_brand NUMBER NOT NULL,
    CONSTRAINT fk_models_brands FOREIGN KEY (id_brand) REFERENCES tb_brands(id)

);