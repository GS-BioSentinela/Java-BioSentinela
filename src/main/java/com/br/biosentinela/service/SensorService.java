package com.br.biosentinela.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.br.biosentinela.model.Sensor;
import com.br.biosentinela.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    private final SensorRepository repository;

    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }

    public Page<Sensor> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Sensor salvar(Sensor sensor) {
        return repository.save(sensor);
    }

    public Sensor atualizar(Long id, Sensor sensorAtualizado) {
        Sensor sensor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado com id: " + id));
        sensor.setTipo(sensorAtualizado.getTipo());
        sensor.setLocalizacao(sensorAtualizado.getLocalizacao());
        sensor.setRegiao(sensorAtualizado.getRegiao());
        return repository.save(sensor);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Sensor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado com id: " + id));
    }
}
