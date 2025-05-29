package com.br.biosentinela.service;

import com.br.biosentinela.dto.SensorDTO;
import com.br.biosentinela.dto.SensorResponse;
import com.br.biosentinela.exception.ResourceNotFoundException;
import com.br.biosentinela.model.Regiao;
import com.br.biosentinela.model.Sensor;
import com.br.biosentinela.repository.RegiaoRepository;
import com.br.biosentinela.repository.SensorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorRepository repository;
    private final RegiaoRepository regiaoRepository;

    public SensorService(SensorRepository repository, RegiaoRepository regiaoRepository) {
        this.repository = repository;
        this.regiaoRepository = regiaoRepository;
    }

    public Page<Sensor> listarPaginado(String tipo, Pageable pageable) {
        if (tipo != null && !tipo.isEmpty()) {
            return repository.findByTipoIgnoreCase(tipo, pageable);
        }
        return repository.findAll(pageable);
    }

    public SensorResponse salvar(SensorDTO dto) {
        Sensor sensor = new Sensor();
        sensor.setTipo(dto.getTipo());
        sensor.setLocalizacao(dto.getLocalizacao());

        Regiao regiao = regiaoRepository.findById(dto.getRegiaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada"));
        sensor.setRegiao(regiao);

        return toResponse(repository.save(sensor));
    }

    public SensorResponse atualizar(Long id, SensorDTO dto) {
        Sensor sensor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));

        sensor.setTipo(dto.getTipo());
        sensor.setLocalizacao(dto.getLocalizacao());

        Regiao regiao = regiaoRepository.findById(dto.getRegiaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada"));
        sensor.setRegiao(regiao);

        return toResponse(repository.save(sensor));
    }

    public void deletar(Long id) {
        Sensor existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));
        repository.delete(existente);
    }

    public SensorResponse buscarPorId(Long id) {
        Sensor sensor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));
        return toResponse(sensor);
    }

    private SensorResponse toResponse(Sensor sensor) {
        SensorResponse response = new SensorResponse();
        response.setId(sensor.getId());
        response.setTipo(sensor.getTipo());
        response.setLocalizacao(sensor.getLocalizacao());
        response.setRegiaoNome(sensor.getRegiao().getNome());
        return response;
    }
}