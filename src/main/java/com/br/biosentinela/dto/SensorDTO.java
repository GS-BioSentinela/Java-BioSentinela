package com.br.biosentinela.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SensorDTO {
    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    @NotBlank(message = "Localização é obrigatória")
    private String localizacao;

    @NotNull(message = "Região é obrigatória")
    private Long regiaoId;
}
