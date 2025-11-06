package br.com.mottu.challange.domain.dto.motorcycle;


import br.com.mottu.challange.domain.entity.Motorcycle;
import br.com.mottu.challange.domain.entity.StatusColor;

public record MotorcycleDetailsDTO(Long id,
                                   String license,
                                   String chassis,
                                   String engine,
                                   StatusColor statusColor,
                                   Long idModel,
                                   Long idDevice) {

    public MotorcycleDetailsDTO(Motorcycle motorcycle) {
        this(motorcycle.getId(),
             motorcycle.getLicense(),
             motorcycle.getChassis(),
             motorcycle.getEngine(),
             motorcycle.getStatusColor(),
             motorcycle.getModel() != null ? motorcycle.getModel().getId() : null,
             motorcycle.getDevice() != null ? motorcycle.getDevice().getId() : null);
    }

}
