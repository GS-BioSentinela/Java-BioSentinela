package com.br.biosentinela.controller;

import com.br.biosentinela.dto.AlertaDTO;
import com.br.biosentinela.dto.AlertaResponse;
import com.br.biosentinela.dto.AlertaStatsDTO;
import com.br.biosentinela.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    private final AlertaService service;

    public AlertaController(AlertaService service) {
        this.service = service;
    }

    @Operation(summary = "Listar alertas", description = "Lista todos os alertas com paginação")
    @GetMapping
    public Page<?> listarPaginado(@ParameterObject Pageable pageable) {
        return service.listarPaginado(pageable);
    }

    @Operation(summary = "Buscar alerta por ID", description = "Retorna os dados de um alerta específico")
    @GetMapping("/{id}")
    public AlertaResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Criar alerta", description = "Cadastra um novo alerta relacionado a um sensor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlertaResponse salvar(@RequestBody @Valid AlertaDTO dto) {
        return service.salvar(dto);
    }

    @Operation(summary = "Atualizar alerta", description = "Atualiza os dados de um alerta existente")
    @PutMapping("/{id}")
    public AlertaResponse atualizar(@PathVariable Long id, @RequestBody @Valid AlertaDTO dto) {
        return service.atualizar(id, dto);
    }

    @Operation(summary = "Deletar alerta", description = "Remove um alerta pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @Operation(summary = "Estatísticas de alertas", description = "Retorna a contagem de alertas por tipo")
    @GetMapping("/stats")
    public List<AlertaStatsDTO> obterEstatisticas() {
        return service.obterEstatisticasPorTipo();
    }
}
