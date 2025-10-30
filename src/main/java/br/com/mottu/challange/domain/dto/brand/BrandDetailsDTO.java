package br.com.mottu.challange.domain.dto.brand;

import br.com.mottu.challange.domain.entity.Brand;

public record BrandDetailsDTO(Long id,
                              String name) {

    public BrandDetailsDTO(Brand brand) {
        this(brand.getId(), brand.getName());
    }

}
