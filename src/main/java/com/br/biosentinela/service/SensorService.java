package com.br.biosentinela.service;

import com.br.biosentinela.dto.SensorDTO;
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

    public Page<Sensor> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Sensor salvar(SensorDTO dto) {
        Sensor sensor = new Sensor();
        sensor.setTipo(dto.getTipo());
        sensor.setLocalizacao(dto.getLocalizacao());

        Regiao regiao = regiaoRepository.findById(dto.getRegiaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada"));
        sensor.setRegiao(regiao);

        return repository.save(sensor);
    }

    public Sensor atualizar(Long id, SensorDTO dto) {
        Sensor sensor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));

        sensor.setTipo(dto.getTipo());
        sensor.setLocalizacao(dto.getLocalizacao());

        Regiao regiao = regiaoRepository.findById(dto.getRegiaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada"));
        sensor.setRegiao(regiao);

        return repository.save(sensor);
    }

    public void deletar(Long id) {
        Sensor existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));
        repository.delete(existente);
    }

    public Sensor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));
    }
}
