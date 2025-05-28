package com.br.biosentinela.service;

import com.br.biosentinela.model.Regiao;
import com.br.biosentinela.repository.RegiaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegiaoService {

    private final RegiaoRepository repository;

    public RegiaoService(RegiaoRepository repository) {
        this.repository = repository;
    }

    public List<Regiao> listarTodas() {
        return repository.findAll();
    }

    public Regiao salvar(Regiao regiao) {
        return repository.save(regiao);
    }

    public Regiao atualizar(Long id, Regiao novaRegiao) {
        Regiao existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Região não encontrada com id: " + id));

        existente.setNome(novaRegiao.getNome());
        existente.setBioma(novaRegiao.getBioma());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        Regiao existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Região não encontrada com id: " + id));

        repository.delete(existente);
    }
}
