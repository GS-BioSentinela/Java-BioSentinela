package com.br.biosentinela.controller;

import com.br.biosentinela.dto.AlertaDTO;
import com.br.biosentinela.dto.AlertaResponse;
import com.br.biosentinela.service.AlertaService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    private final AlertaService service;

    public AlertaController(AlertaService service) {
        this.service = service;
    }

    @GetMapping
    public Page<?> listarPaginado(@ParameterObject Pageable pageable) {
        return service.listarPaginado(pageable);
    }

    @GetMapping("/{id}")
    public AlertaResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlertaResponse salvar(@RequestBody @Valid AlertaDTO dto) {
        return service.salvar(dto);
    }

    @PutMapping("/{id}")
    public AlertaResponse atualizar(@PathVariable Long id, @RequestBody @Valid AlertaDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
