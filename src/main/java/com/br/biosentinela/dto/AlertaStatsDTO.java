package com.br.biosentinela.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlertaStatsDTO {
    private String tipo;
    private Long quantidade;
}
