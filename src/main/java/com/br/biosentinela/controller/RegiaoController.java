package com.br.biosentinela.controller;

import com.br.biosentinela.model.Regiao;
import com.br.biosentinela.service.RegiaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regioes")
public class RegiaoController {

    private final RegiaoService service;

    public RegiaoController(RegiaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Regiao> listar() {
        return service.listarTodas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Regiao salvar(@RequestBody @Valid Regiao regiao) {
        return service.salvar(regiao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Regiao> atualizar(@PathVariable Long id, @RequestBody @Valid Regiao regiaoAtualizada) {
        Regiao regiao = service.atualizar(id, regiaoAtualizada);
        return ResponseEntity.ok(regiao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
