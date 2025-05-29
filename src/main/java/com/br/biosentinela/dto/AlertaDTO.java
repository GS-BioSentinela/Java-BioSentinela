package com.br.biosentinela.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlertaDTO {

    @NotBlank(message = "Tipo de alerta é obrigatório")
    private String tipo;

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;

    @NotNull(message = "ID do sensor é obrigatório")
    private Long sensorId;
}
