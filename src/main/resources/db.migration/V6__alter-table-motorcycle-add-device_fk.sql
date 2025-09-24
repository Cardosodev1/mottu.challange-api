ALTER TABLE motorcycles
    ADD CONSTRAINT fk_motorcycles_devices FOREIGN KEY (id_device) REFERENCES devices(id);
