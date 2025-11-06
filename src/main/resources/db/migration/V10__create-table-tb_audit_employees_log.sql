CREATE TABLE tb_audit_employees_log (

    id NUMBER PRIMARY KEY,
    operacao VARCHAR2(20) NOT NULL,
    data_hora TIMESTAMP,
    valores_antigos VARCHAR2(255),
    valores_novos VARCHAR2(255),
    id_employee NUMBER,
    CONSTRAINT fk_audit_log_employees FOREIGN KEY (id_employee) REFERENCES tb_employees(id)

);