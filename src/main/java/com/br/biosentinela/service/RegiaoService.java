package com.br.biosentinela.service;

import com.br.biosentinela.dto.RegiaoDTO;
import com.br.biosentinela.exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com id: " + id));
    }

    public Regiao salvar(RegiaoDTO dto) {
        Regiao regiao = new Regiao();
        regiao.setNome(dto.getNome());
        regiao.setBioma(dto.getBioma());
        return repository.save(regiao);
    }

    public Regiao atualizar(Long id, RegiaoDTO dto) {
        Regiao existente = buscarPorId(id);
        existente.setNome(dto.getNome());
        existente.setBioma(dto.getBioma());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        Regiao existente = buscarPorId(id);
        repository.delete(existente);
    }
}
