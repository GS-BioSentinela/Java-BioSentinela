package com.br.biosentinela.service;

import com.br.biosentinela.model.Alerta;
import com.br.biosentinela.repository.AlertaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {

    private final AlertaRepository repository;

    public AlertaService(AlertaRepository repository) {
        this.repository = repository;
    }

    public Page<Alerta> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Alerta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado com id: " + id));
    }

    public Alerta salvar(Alerta alerta) {
        return repository.save(alerta);
    }

    public Alerta atualizar(Long id, Alerta alertaAtualizado) {
        alertaAtualizado.setId(id);
        return repository.save(alertaAtualizado);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
