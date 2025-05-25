package br.com.mottu.challange.entity;

import br.com.mottu.challange.dto.yard.YardDTO;
import br.com.mottu.challange.dto.yard.YardDetailsDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "yards")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Yard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unit")
    private Unit unit;

    public Yard(Long idYard) {
        this.id = idYard;
    }

    public Yard(YardDTO yardDTO) {
        this.name = yardDTO.name();
        this.unit = new Unit(yardDTO.idUnit());
    }

    public void update(@Valid YardDetailsDTO yardDetailsDTO) {
        if (yardDetailsDTO.name() != null) {
            this.name = yardDetailsDTO.name();
        }
        if (yardDetailsDTO.idUnit() != null) {
            this.unit = new Unit(yardDetailsDTO.idUnit());
        }
    }
}
