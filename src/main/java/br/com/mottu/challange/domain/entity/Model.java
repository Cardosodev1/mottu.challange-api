package br.com.mottu.challange.domain.entity;

import br.com.mottu.challange.domain.dto.model.ModelDTO;
import br.com.mottu.challange.domain.dto.model.ModelDetailsDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_models")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_seq_generator")
    @SequenceGenerator(name = "model_seq_generator", sequenceName = "model_seq", allocationSize = 1)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_brand")
    private Brand brand;

    public Model(Long idModel) {
        this.id = idModel;
    }

    public Model(ModelDTO modelDTO) {
        this.name = modelDTO.name();
        this.brand = new Brand(modelDTO.idBrand());
    }

    public void update(@Valid ModelDetailsDTO modelDetailsDTO) {
        if (modelDetailsDTO.name() != null) {
            this.name = modelDetailsDTO.name();
        }
        if (modelDetailsDTO.idBrand() != null) {
            this.brand = new Brand(modelDetailsDTO.idBrand());
        }
    }

}