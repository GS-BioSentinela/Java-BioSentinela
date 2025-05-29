package com.br.biosentinela.controller;

import com.br.biosentinela.dto.SensorDTO;
import com.br.biosentinela.model.Sensor;
import com.br.biosentinela.service.SensorService;
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

    @GetMapping
    public Page<Sensor> listarPaginado(
            @RequestParam(required = false) String tipo,
            @ParameterObject Pageable pageable) {
        return service.listarPaginado(tipo, pageable);
    }

    @GetMapping("/{id}")
    public Sensor buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor salvar(@RequestBody @Valid SensorDTO dto) {
        return service.salvar(dto);
    }

    @PutMapping("/{id}")
    public Sensor atualizar(@PathVariable Long id, @RequestBody @Valid SensorDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
