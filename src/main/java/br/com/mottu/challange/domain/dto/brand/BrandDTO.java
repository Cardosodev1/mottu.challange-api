package br.com.mottu.challange.domain.dto.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BrandDTO(

        @NotNull
        @NotBlank
        String name

) {
}
