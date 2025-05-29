package com.br.biosentinela.dto;

import lombok.Data;

@Data
public class AlertaResponse {
    private Long id;
    private String tipo;
    private String mensagem;
    private String sensorLocalizacao;
}
