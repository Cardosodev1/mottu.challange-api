package br.com.mottu.challange.domain.entity;

import br.com.mottu.challange.domain.dto.device.DeviceDTO;
import br.com.mottu.challange.domain.dto.device.DeviceDetailsDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_devices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_seq_generator")
    @SequenceGenerator(name = "device_seq_generator", sequenceName = "device_seq", allocationSize = 1)
    private Long id;

    private String code;

    private Double distance;
    private LocalDateTime reading_timestamp;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_yard")
    private Yard yard;

    @OneToOne
    @JoinColumn(name = "id_motorcycle")
    private Motorcycle motorcycle;

    public Device(Long idDevice) {
        this.id = idDevice;
    }

    public Device(DeviceDTO deviceDTO) {
        this.code = deviceDTO.code();
        this.distance = deviceDTO.distance();
        this.reading_timestamp = deviceDTO.reading_timestamp();
        this.active = deviceDTO.active();
        this.yard = new Yard(deviceDTO.idYard());
        if (deviceDTO.idMotorcycle() != null) {
            this.motorcycle = new Motorcycle(deviceDTO.idMotorcycle());
        }
    }

    public void update(@Valid DeviceDetailsDTO deviceDetailsDTO) {
        if (deviceDetailsDTO.code() != null) {
            this.code = deviceDetailsDTO.code();
        }
        if (deviceDetailsDTO.distance() != null) {
            this.distance = deviceDetailsDTO.distance();
        }
        if (deviceDetailsDTO.reading_timestamp() != null) {
            this.reading_timestamp = deviceDetailsDTO.reading_timestamp();
        }
        if (deviceDetailsDTO.active() != null) {
            this.active = deviceDetailsDTO.active();
        }
        if (deviceDetailsDTO.idYard() != null) {
            this.yard = new Yard(deviceDetailsDTO.idYard());
        }
        if (deviceDetailsDTO.idMotorcycle() != null) {
            this.motorcycle = new Motorcycle(deviceDetailsDTO.idMotorcycle());
        }
    }
}
