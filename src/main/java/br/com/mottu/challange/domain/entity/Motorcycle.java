package br.com.mottu.challange.domain.entity;

import br.com.mottu.challange.domain.dto.motorcycle.MotorcycleDTO;
import br.com.mottu.challange.domain.dto.motorcycle.MotorcycleDetailsDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_motorcycles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motorcycle_seq_generator")
    @SequenceGenerator(name = "motorcycle_seq_generator", sequenceName = "motorcycle_seq", allocationSize = 1)
    private Long id;

    private String license;
    private String chassis;
    private String engine;

    @Enumerated(EnumType.STRING)
    private StatusColor statusColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_model")
    private Model model;

    @OneToOne
    @JoinColumn(name = "id_device")
    private Device device;

    public Motorcycle(Long idMotorcycle) {
        this.id = idMotorcycle;
    }

    public Motorcycle(MotorcycleDTO motorcycleDTO) {
        this.license = motorcycleDTO.license();
        this.chassis = motorcycleDTO.chassis();
        this.engine = motorcycleDTO.engine();
        this.statusColor = motorcycleDTO.statusColor();
        if (motorcycleDTO.idModel() != null) {
            this.model = new Model(motorcycleDTO.idModel());
        }
        if (motorcycleDTO.idDevice() != null) {
            this.device = new Device(motorcycleDTO.idDevice());
        }
    }

    public void update(@Valid MotorcycleDetailsDTO motorcycleDetailsDTO) {
        if (motorcycleDetailsDTO.license() != null) {
            this.license = motorcycleDetailsDTO.license();
        }
        if (motorcycleDetailsDTO.chassis() != null) {
            this.chassis = motorcycleDetailsDTO.chassis();
        }
        if (motorcycleDetailsDTO.engine() != null) {
            this.engine = motorcycleDetailsDTO.engine();
        }
        if (motorcycleDetailsDTO.statusColor() != null) {
            this.statusColor = motorcycleDetailsDTO.statusColor();
        }
        if (motorcycleDetailsDTO.idModel() != null) {
            this.model = new Model(motorcycleDetailsDTO.idModel());
        }
        if (motorcycleDetailsDTO.idDevice() != null) {
            this.device = new Device(motorcycleDetailsDTO.idDevice());
        }
    }
}
