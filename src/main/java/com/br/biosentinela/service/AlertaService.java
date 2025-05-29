package com.br.biosentinela.service;

import com.br.biosentinela.dto.AlertaDTO;
import com.br.biosentinela.model.Alerta;
import com.br.biosentinela.model.Sensor;
import com.br.biosentinela.repository.AlertaRepository;
import com.br.biosentinela.repository.SensorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {

    private final AlertaRepository repository;
    private final SensorRepository sensorRepository;

    public AlertaService(AlertaRepository repository, SensorRepository sensorRepository) {
        this.repository = repository;
        this.sensorRepository = sensorRepository;
    }

    public Page<Alerta> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Alerta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado com id: " + id));
    }

    public Alerta salvar(AlertaDTO dto) {
        Sensor sensor = sensorRepository.findById(dto.getSensorId())
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        Alerta alerta = new Alerta();
        alerta.setTipo(dto.getTipo());
        alerta.setMensagem(dto.getMensagem());
        alerta.setSensor(sensor);

        return repository.save(alerta);
    }

    public Alerta atualizar(Long id, AlertaDTO dto) {
        Alerta alerta = buscarPorId(id);
        Sensor sensor = sensorRepository.findById(dto.getSensorId())
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        alerta.setTipo(dto.getTipo());
        alerta.setMensagem(dto.getMensagem());
        alerta.setSensor(sensor);

        return repository.save(alerta);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
