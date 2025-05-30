package br.com.mottu.challange.dto.unit;

import br.com.mottu.challange.entity.Unit;

public record UnitDetailsDTO(Long id,
                             String name,
                             String address,
                             String neighborhood) {

    public UnitDetailsDTO(Unit unit) {
        this(unit.getId(), unit.getName(), unit.getAddress(), unit.getNeighborhood());
    }

}
