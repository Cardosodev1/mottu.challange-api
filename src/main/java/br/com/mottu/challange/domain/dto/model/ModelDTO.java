package br.com.mottu.challange.domain.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModelDTO(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        Long idBrand

) {
}
