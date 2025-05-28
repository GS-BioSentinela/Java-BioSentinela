package com.br.biosentinela.controller;

import com.br.biosentinela.model.Alerta;
import com.br.biosentinela.service.AlertaService;
import jakarta.validation.Valid;
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

    @GetMapping
    public List<Alerta> listar() {
        return service.listarTodos();
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
