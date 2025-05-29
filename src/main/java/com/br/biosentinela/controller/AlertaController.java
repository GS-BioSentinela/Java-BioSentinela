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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<?>> listarPaginado(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(pageable));
    }

    @Operation(summary = "Buscar alerta por ID", description = "Retorna os dados de um alerta específico")
    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar alerta", description = "Cadastra um novo alerta relacionado a um sensor")
    @PostMapping
    public ResponseEntity<AlertaResponse> salvar(@RequestBody @Valid AlertaDTO dto) {
        return ResponseEntity.status(201).body(service.salvar(dto));
    }

    @Operation(summary = "Atualizar alerta", description = "Atualiza os dados de um alerta existente")
    @PutMapping("/{id}")
    public ResponseEntity<AlertaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AlertaDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deletar alerta", description = "Remove um alerta pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Estatísticas de alertas", description = "Retorna a contagem de alertas por tipo")
    @GetMapping("/stats")
    public ResponseEntity<List<AlertaStatsDTO>> obterEstatisticas() {
        return ResponseEntity.ok(service.obterEstatisticasPorTipo());
    }
}
