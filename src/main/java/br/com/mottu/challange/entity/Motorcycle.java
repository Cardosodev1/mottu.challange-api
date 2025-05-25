package br.com.mottu.challange.entity;

import br.com.mottu.challange.dto.motorcycle.MotorcycleDTO;
import br.com.mottu.challange.dto.motorcycle.MotorcycleUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "motorcycles")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String license;
    private String chassis;
    private String engine;
    private String brand;
    private String model;

    @OneToOne
    private Device device;

    public Motorcycle(Long idMotorcycle) {
        this.id = idMotorcycle;
    }

    public Motorcycle(MotorcycleDTO motorcycleDTO) {
        this.license = motorcycleDTO.license();
        this.chassis = motorcycleDTO.chassis();
        this.engine = motorcycleDTO.engine();
        this.brand = motorcycleDTO.brand();
        this.model = motorcycleDTO.model();
        if (motorcycleDTO.idDevice() != null) {
            this.device = new Device(motorcycleDTO.idDevice());
        }
    }

    public void update(@Valid MotorcycleUpdateDTO motorcycleUpdateDTO) {
        if (motorcycleUpdateDTO.license() != null) {
            this.license = motorcycleUpdateDTO.license();
        }
        if (motorcycleUpdateDTO.chassis() != null) {
            this.chassis = motorcycleUpdateDTO.chassis();
        }
        if (motorcycleUpdateDTO.engine() != null) {
            this.engine = motorcycleUpdateDTO.engine();
        }
        if (motorcycleUpdateDTO.idDevice() != null) {
            this.device = new Device(motorcycleUpdateDTO.idDevice());
        }
    }
}
