package com.br.biosentinela.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErroDTO {

    private String mensagem;
    private Object detalhes;

}
