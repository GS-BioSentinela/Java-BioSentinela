package com.br.biosentinela.controller;

import com.br.biosentinela.dto.RegiaoDTO;
import com.br.biosentinela.model.Regiao;
import com.br.biosentinela.service.RegiaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regioes")
public class RegiaoController {

    private final RegiaoService service;

    public RegiaoController(RegiaoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar regiões", description = "Lista todas as regiões cadastradas com paginação")
    @GetMapping
    public Page<Regiao> listarPaginado(@ParameterObject Pageable pageable) {
        return service.listarPaginado(pageable);
    }

    @Operation(summary = "Buscar região por ID", description = "Retorna os dados de uma região específica")
    @GetMapping("/{id}")
    public ResponseEntity<Regiao> buscarPorId(@PathVariable Long id) {
        Regiao regiao = service.buscarPorId(id);
        return ResponseEntity.ok(regiao);
    }

    @Operation(summary = "Criar nova região", description = "Cadastra uma nova região com nome e bioma")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Regiao salvar(@RequestBody @Valid RegiaoDTO dto) {
        return service.salvar(dto);
    }

    @Operation(summary = "Atualizar região", description = "Atualiza os dados de uma região existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Regiao> atualizar(@PathVariable Long id, @RequestBody @Valid RegiaoDTO dto) {
        Regiao regiao = service.atualizar(id, dto);
        return ResponseEntity.ok(regiao);
    }

    @Operation(summary = "Deletar região", description = "Remove uma região existente pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
