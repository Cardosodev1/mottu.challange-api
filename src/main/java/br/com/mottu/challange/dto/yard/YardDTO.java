package br.com.mottu.challange.dto.yard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record YardDTO(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        Long idUnit

) {
}
