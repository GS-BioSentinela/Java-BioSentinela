package com.br.biosentinela.controller;

import com.br.biosentinela.dto.SensorDTO;
import com.br.biosentinela.model.Sensor;
import com.br.biosentinela.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public Page<Sensor> listarPaginado(
            @RequestParam(required = false) String tipo,
            @ParameterObject Pageable pageable) {
        return service.listarPaginado(tipo, pageable);
    }

    @Operation(summary = "Buscar sensor por ID", description = "Retorna os detalhes de um sensor específico")
    @GetMapping("/{id}")
    public Sensor buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Criar sensor", description = "Cadastra um novo sensor com tipo, localização e região")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor salvar(@RequestBody @Valid SensorDTO dto) {
        return service.salvar(dto);
    }

    @Operation(summary = "Atualizar sensor", description = "Atualiza os dados de um sensor existente pelo ID")
    @PutMapping("/{id}")
    public Sensor atualizar(@PathVariable Long id, @RequestBody @Valid SensorDTO dto) {
        return service.atualizar(id, dto);
    }

    @Operation(summary = "Deletar sensor", description = "Remove um sensor existente pelo ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
