package com.br.biosentinela.service;

import com.br.biosentinela.dto.RegiaoDTO;
import com.br.biosentinela.dto.RegiaoResponse;
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

    public Page<RegiaoResponse> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toResponse);
    }

    public RegiaoResponse buscarPorId(Long id) {
        Regiao regiao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com id: " + id));
        return toResponse(regiao);
    }

    public RegiaoResponse salvar(RegiaoDTO dto) {
        Regiao regiao = new Regiao();
        regiao.setNome(dto.getNome());
        regiao.setBioma(dto.getBioma());
        return toResponse(repository.save(regiao));
    }

    public RegiaoResponse atualizar(Long id, RegiaoDTO dto) {
        Regiao existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com id: " + id));
        existente.setNome(dto.getNome());
        existente.setBioma(dto.getBioma());
        return toResponse(repository.save(existente));
    }

    public void deletar(Long id) {
        Regiao existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Região não encontrada com id: " + id));
        repository.delete(existente);
    }

    private RegiaoResponse toResponse(Regiao regiao) {
        RegiaoResponse response = new RegiaoResponse();
        response.setId(regiao.getId());
        response.setNome(regiao.getNome());
        response.setBioma(regiao.getBioma());
        return response;
    }
}