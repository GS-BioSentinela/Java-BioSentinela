package com.br.biosentinela.controller;

import com.br.biosentinela.dto.SensorDTO;
import com.br.biosentinela.dto.SensorResponse;
import com.br.biosentinela.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @Operation(summary = "Listar sensores", description = "Lista todos os sensores com paginação e filtro por tipo (opcional)")
    @GetMapping
    public ResponseEntity<Page<SensorResponse>> listarPaginado(
            @RequestParam(required = false) String tipo,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(tipo, pageable));
    }


    @Operation(summary = "Buscar sensor por ID", description = "Retorna os detalhes de um sensor específico")
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar sensor", description = "Cadastra um novo sensor com tipo, localização e região")
    @PostMapping
    public ResponseEntity<SensorResponse> salvar(@RequestBody @Valid SensorDTO dto) {
        return ResponseEntity.status(201).body(service.salvar(dto));
    }

    @Operation(summary = "Atualizar sensor", description = "Atualiza os dados de um sensor existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<SensorResponse> atualizar(@PathVariable Long id, @RequestBody @Valid SensorDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deletar sensor", description = "Remove um sensor existente pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
