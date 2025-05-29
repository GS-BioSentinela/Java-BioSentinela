package com.br.biosentinela.service;

import com.br.biosentinela.model.Regiao;
import com.br.biosentinela.repository.RegiaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegiaoService {

    private final RegiaoRepository repository;

    public RegiaoService(RegiaoRepository repository) {
        this.repository = repository;
    }

    public Page<Regiao> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Regiao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Região não encontrada com id: " + id));
    }

    public Regiao salvar(Regiao regiao) {
        return repository.save(regiao);
    }

    public Regiao atualizar(Long id, Regiao novaRegiao) {
        Regiao existente = buscarPorId(id);
        existente.setNome(novaRegiao.getNome());
        existente.setBioma(novaRegiao.getBioma());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        Regiao existente = buscarPorId(id);
        repository.delete(existente);
    }
}
