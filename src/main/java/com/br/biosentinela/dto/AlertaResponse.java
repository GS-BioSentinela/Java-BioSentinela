package com.br.biosentinela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AlertaResponse {

    @Schema(description = "ID do alerta")
    private Long id;

    @Schema(description = "Tipo do alerta")
    private String tipo;

    @Schema(description = "Mensagem do alerta")
    private String mensagem;

    @Schema(description = "Localização do sensor associado")
    private String sensorLocalizacao;
}