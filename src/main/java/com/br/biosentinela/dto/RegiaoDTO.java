package com.br.biosentinela.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegiaoDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome da região monitorada (ex: Amazônia Sul)")
    private String nome;

    @Schema(description = "Tipo de bioma predominante (ex: Amazônico, Cerrado)")
    private String bioma;
}
