package br.com.mottu.challange.dto.device;

import br.com.mottu.challange.entity.Device;
import br.com.mottu.challange.entity.StatusColor;

public record DeviceDetailsDTO(Long id,
                               String code,
                               StatusColor statusColor,
                               Boolean active,
                               Long idYard,
                               Long idMotorcycle) {

    public DeviceDetailsDTO(Device device) {
        this(device.getId(), device.getCode(), device.getStatusColor(), device.getActive(), device.getYard().getId(), device.getMotorcycle() != null ? device.getMotorcycle().getId() : null);
    }

}
