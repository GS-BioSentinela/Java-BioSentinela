package com.br.biosentinela.controller;

import com.br.biosentinela.model.Alerta;
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
    public Page<Alerta> listarPaginado(@ParameterObject Pageable pageable) {
        return service.listarPaginado(pageable);
    }

    @GetMapping("/{id}")
    public Alerta buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alerta salvar(@RequestBody @Valid Alerta alerta) {
        return service.salvar(alerta);
    }

    @PutMapping("/{id}")
    public Alerta atualizar(@PathVariable Long id, @RequestBody @Valid Alerta alerta) {
        return service.atualizar(id, alerta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
