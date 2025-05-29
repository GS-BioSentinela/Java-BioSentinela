package com.br.biosentinela.controller;

import com.br.biosentinela.model.Regiao;
import com.br.biosentinela.service.RegiaoService;
import jakarta.validation.Valid;
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

    @GetMapping
    public Page<Regiao> listarPaginado(Pageable pageable) {
        return service.listarPaginado(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Regiao> buscarPorId(@PathVariable Long id) {
        Regiao regiao = service.buscarPorId(id);
        return ResponseEntity.ok(regiao);
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
