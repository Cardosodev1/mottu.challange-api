package br.com.mottu.challange.domain.dto.device;

import br.com.mottu.challange.domain.entity.Device;
import br.com.mottu.challange.domain.entity.StatusColor;

import java.time.LocalDateTime;

public record DeviceDetailsDTO(Long id,
                               String code,
                               Double distance,
                               LocalDateTime reading_timestamp,
                               Boolean active,
                               Long idYard,
                               Long idMotorcycle) {

    public DeviceDetailsDTO(Device device) {
        this(device.getId(),
                device.getCode(),
                device.getDistance(),
                device.getReading_timestamp(),
                device.getActive(),
                device.getYard().getId(),
                device.getMotorcycle() != null ? device.getMotorcycle().getId() : null);
    }

}
