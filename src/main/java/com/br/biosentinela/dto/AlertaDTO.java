package com.br.biosentinela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlertaDTO {

    @NotBlank(message = "Tipo de alerta é obrigatório")
    @Schema(description = "Tipo do alerta (ex: Fumaça, Calor)")
    private String tipo;

    @NotBlank(message = "Mensagem é obrigatória")
    @Schema(description = "Mensagem detalhada sobre o alerta")
    private String mensagem;

    @NotNull(message = "ID do sensor é obrigatório")
    @Schema(description = "ID do sensor relacionado ao alerta")
    private Long sensorId;
}
