package br.com.mottu.challange.domain.dto.model;

import br.com.mottu.challange.domain.entity.Model;

public record ModelDetailsDTO(Long id,
                              String name,
                              Long idBrand) {

    public ModelDetailsDTO(Model model) {
        this(model.getId(), model.getName(), model.getBrand().getId());
    }

}
