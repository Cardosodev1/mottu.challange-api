package br.com.mottu.challange.entity;

import br.com.mottu.challange.dto.unit.UnitDTO;
import br.com.mottu.challange.dto.unit.UnitDetailsDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "units")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String neighborhood;

    public Unit(Long idUnit) {
        this.id = idUnit;
    }

    public Unit(UnitDTO unitDTO) {
        this.name = unitDTO.name();
        this.address = unitDTO.address();
        this.neighborhood = unitDTO.neighborhood();
    }

    public void update(@Valid UnitDetailsDTO unitDetailsDTO) {
        if (unitDetailsDTO.name() != null) {
            this.name = unitDetailsDTO.name();
        }
        if (unitDetailsDTO.address() != null) {
            this.address = unitDetailsDTO.address();
        }
        if (unitDetailsDTO.neighborhood() != null) {
            this.neighborhood = unitDetailsDTO.neighborhood();
        }
    }

}
