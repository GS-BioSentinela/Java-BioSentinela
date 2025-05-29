package com.br.biosentinela.dto;

import lombok.Data;

@Data
public class SensorResponse {
    private Long id;
    private String tipo;
    private String localizacao;
    private String regiaoNome;
}
