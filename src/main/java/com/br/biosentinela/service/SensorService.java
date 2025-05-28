package com.br.biosentinela.service;

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

    public List<Sensor> listarTodos() {
        return repository.findAll();
    }

    public Sensor salvar(Sensor sensor) {
        return repository.save(sensor);
    }

    public Sensor atualizar(Long id, Sensor sensorAtualizado) {
        Sensor sensor = repository.findById(id).orElseThrow();
        sensor.setTipo(sensorAtualizado.getTipo());
        sensor.setLocalizacao(sensorAtualizado.getLocalizacao());
        sensor.setRegiao(sensorAtualizado.getRegiao());
        return repository.save(sensor);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
