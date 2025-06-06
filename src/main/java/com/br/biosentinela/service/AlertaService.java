package com.br.biosentinela.service;

import com.br.biosentinela.dto.AlertaDTO;
import com.br.biosentinela.dto.AlertaResponse;
import com.br.biosentinela.dto.AlertaStatsDTO;
import com.br.biosentinela.exception.ResourceNotFoundException;
import com.br.biosentinela.model.Alerta;
import com.br.biosentinela.model.Sensor;
import com.br.biosentinela.repository.AlertaRepository;
import com.br.biosentinela.repository.SensorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {

    private final AlertaRepository repository;
    private final SensorRepository sensorRepository;

    public AlertaService(AlertaRepository repository, SensorRepository sensorRepository) {
        this.repository = repository;
        this.sensorRepository = sensorRepository;
    }

    public Page<AlertaResponse> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toResponse);
    }


    public AlertaResponse buscarPorId(Long id) {
        return toResponse(buscarEntidade(id));
    }

    public AlertaResponse salvar(AlertaDTO dto) {
        Sensor sensor = sensorRepository.findById(dto.getSensorId())
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado"));

        Alerta alerta = new Alerta();
        alerta.setTipo(dto.getTipo());
        alerta.setMensagem(dto.getMensagem());
        alerta.setSensor(sensor);

        return toResponse(repository.save(alerta));
    }

    public AlertaResponse atualizar(Long id, AlertaDTO dto) {
        Alerta alerta = buscarEntidade(id);
        Sensor sensor = sensorRepository.findById(dto.getSensorId())
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado"));

        alerta.setTipo(dto.getTipo());
        alerta.setMensagem(dto.getMensagem());
        alerta.setSensor(sensor);

        return toResponse(repository.save(alerta));
    }

    public void deletar(Long id) {
        Alerta existente = buscarEntidade(id);
        repository.delete(existente);
    }

    public List<AlertaStatsDTO> obterEstatisticasPorTipo() {
        return repository.contarAlertasPorTipo();
    }

    private Alerta buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com id: " + id));
    }

    private AlertaResponse toResponse(Alerta alerta) {
        AlertaResponse response = new AlertaResponse();
        response.setId(alerta.getId());
        response.setTipo(alerta.getTipo());
        response.setMensagem(alerta.getMensagem());
        response.setSensorLocalizacao(alerta.getSensor().getLocalizacao());
        return response;
    }
}
