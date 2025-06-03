package com.br.biosentinela.controller;

import com.br.biosentinela.dto.RegiaoDTO;
import com.br.biosentinela.dto.RegiaoResponse;
import com.br.biosentinela.service.RegiaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/regioes")
public class RegiaoController {

    private final RegiaoService service;

    public RegiaoController(RegiaoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar regiões", description = "Lista todas as regiões cadastradas com paginação")
    @GetMapping
    public ResponseEntity<Page<RegiaoResponse>> listarPaginado(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(pageable));
    }

    @Operation(summary = "Buscar região por ID", description = "Retorna os dados de uma região específica")
    @GetMapping("/{id}")
    public ResponseEntity<RegiaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar nova região", description = "Cadastra uma nova região com nome e bioma")
    @PostMapping
    public ResponseEntity<RegiaoResponse> salvar(@RequestBody @Valid RegiaoDTO dto) {
        return ResponseEntity.status(201).body(service.salvar(dto));
    }

    @Operation(summary = "Atualizar região", description = "Atualiza os dados de uma região existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<RegiaoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid RegiaoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deletar região", description = "Remove uma região existente pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
