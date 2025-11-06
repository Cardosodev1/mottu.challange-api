package br.com.mottu.challange.domain.dto.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DeviceDTO(

        @NotNull
        @NotBlank
        String code,

        Double distance,

        LocalDateTime reading_timestamp,

        Boolean active,

        @NotNull
        Long idYard,

        Long idMotorcycle

) {
}
