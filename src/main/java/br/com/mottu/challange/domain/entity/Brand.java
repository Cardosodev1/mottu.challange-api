package br.com.mottu.challange.domain.entity;

import br.com.mottu.challange.domain.dto.brand.BrandDTO;
import br.com.mottu.challange.domain.dto.brand.BrandDetailsDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_seq_generator")
    @SequenceGenerator(name = "brand_seq_generator", sequenceName = "brand_seq", allocationSize = 1)
    private Long id;

    private String name;

    public Brand(Long idBrand) {
        this.id = idBrand;
    }

    public Brand(BrandDTO brandDTO) {
        this.name = brandDTO.name();
    }

    public void update(@Valid BrandDetailsDTO brandDetailsDTO) {
        if (brandDetailsDTO.name() != null) {
            this.name = brandDetailsDTO.name();
        }
    }

}