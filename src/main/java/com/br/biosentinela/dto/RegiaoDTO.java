package com.br.biosentinela.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegiaoDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String bioma;
}
