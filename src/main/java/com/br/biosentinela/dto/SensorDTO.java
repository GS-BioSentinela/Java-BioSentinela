package com.br.biosentinela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SensorDTO {

    @NotBlank(message = "Tipo é obrigatório")
    @Schema(description = "Tipo do sensor, ex: Temperatura, Umidade")
    private String tipo;

    @NotBlank(message = "Localização é obrigatória")
    @Schema(description = "Localização física do sensor (ex: coordenadas GPS)")
    private String localizacao;

    @NotNull(message = "Região é obrigatória")
    @Schema(description = "ID da região à qual o sensor está associado")
    private Long regiaoId;
}
