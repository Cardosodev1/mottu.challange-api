CREATE TABLE tb_devices (

    id NUMBER PRIMARY KEY,
    code VARCHAR2(100) NOT NULL,
    distance NUMBER,
    reading_timestamp TIMESTAMP,
    active NUMBER(1) CHECK (active IN (0,1)),
    id_yard NUMBER NOT NULL,
    id_motorcycle NUMBER UNIQUE,
    CONSTRAINT fk_devices_yards FOREIGN KEY (id_yard) REFERENCES tb_yards(id),
    CONSTRAINT fk_devices_motorcycles FOREIGN KEY (id_motorcycle) REFERENCES tb_motorcycles(id)

);
