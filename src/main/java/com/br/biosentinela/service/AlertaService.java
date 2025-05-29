package com.br.biosentinela.service;

import com.br.biosentinela.model.Alerta;
import com.br.biosentinela.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {

    private final AlertaRepository repository;

    public AlertaService(AlertaRepository repository) {
        this.repository = repository;
    }

    public List<Alerta> listarTodos() {
        return repository.findAll();
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

    public Alerta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado com id: " + id));
    }
}
