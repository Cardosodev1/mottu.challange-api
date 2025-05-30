package br.com.mottu.challange.entity;

import br.com.mottu.challange.dto.device.DeviceDTO;
import br.com.mottu.challange.dto.device.DeviceDetailsDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devices")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Enumerated(EnumType.STRING)
    private StatusColor statusColor;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_yard")
    private Yard yard;

    @OneToOne
    private Motorcycle motorcycle;

    public Device(Long idDevice) {
        this.id = idDevice;
    }

    public Device(DeviceDTO deviceDTO) {
        this.code = deviceDTO.code();
        this.statusColor = deviceDTO.statusColor();
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
        if (deviceDetailsDTO.statusColor() != null) {
            this.statusColor = deviceDetailsDTO.statusColor();
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
