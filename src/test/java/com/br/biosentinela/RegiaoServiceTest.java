
package com.br.biosentinela;

import com.br.biosentinela.dto.RegiaoDTO;
import com.br.biosentinela.dto.RegiaoResponse;
import com.br.biosentinela.exception.ResourceNotFoundException;
import com.br.biosentinela.model.Regiao;
import com.br.biosentinela.repository.RegiaoRepository;
import com.br.biosentinela.service.RegiaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegiaoServiceTest {

    @Mock
    private RegiaoRepository regiaoRepository;

    @InjectMocks
    private RegiaoService regiaoService;

    private Regiao regiao;
    private RegiaoDTO regiaoDTO;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        regiao = new Regiao(1L, "Pantanal", "Pantanal");
        regiaoDTO = new RegiaoDTO();
        regiaoDTO.setNome("Pantanal");
        regiaoDTO.setBioma("Pantanal");

        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("Deve listar regiões paginadas com sucesso")
    void listarPaginado_DeveRetornarPaginaDeRegioes() {
        Page<Regiao> page = new PageImpl<>(Collections.singletonList(regiao), pageable, 1);
        when(regiaoRepository.findAll(pageable)).thenReturn(page);
        Page<RegiaoResponse> result = regiaoService.listarPaginado(pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals(regiao.getNome(), result.getContent().get(0).getNome());
        verify(regiaoRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Deve buscar região por ID com sucesso")
    void buscarPorId_QuandoIdExiste_DeveRetornarRegiao() {
        when(regiaoRepository.findById(1L)).thenReturn(Optional.of(regiao));
        RegiaoResponse result = regiaoService.buscarPorId(1L);
        assertNotNull(result);
        assertEquals(regiao.getId(), result.getId());
        assertEquals(regiao.getNome(), result.getNome());
        verify(regiaoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao buscar região por ID inexistente")
    void buscarPorId_QuandoIdNaoExiste_DeveLancarExcecao() {
        when(regiaoRepository.findById(99L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            regiaoService.buscarPorId(99L);
        });
        assertEquals("Região não encontrada com id: 99", exception.getMessage());
        verify(regiaoRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Deve salvar uma nova região com sucesso")
    void salvar_QuandoDadosValidos_DeveRetornarRegiaoSalva() {
        when(regiaoRepository.save(any(Regiao.class))).thenAnswer(invocation -> {
            Regiao r = invocation.getArgument(0);
            r.setId(2L); // Simula ID gerado
            return r;
        });
        RegiaoResponse result = regiaoService.salvar(regiaoDTO);
        assertNotNull(result);
        assertEquals(regiaoDTO.getNome(), result.getNome());
        assertEquals(regiaoDTO.getBioma(), result.getBioma());
        assertNotNull(result.getId());
        verify(regiaoRepository, times(1)).save(any(Regiao.class));
    }

    @Test
    @DisplayName("Deve atualizar uma região existente com sucesso")
    void atualizar_QuandoDadosValidosEIdExiste_DeveRetornarRegiaoAtualizada() {
        // Arrange
        RegiaoDTO dtoAtualizado = new RegiaoDTO();
        dtoAtualizado.setNome("Cerrado");
        dtoAtualizado.setBioma("Cerrado");

        when(regiaoRepository.findById(1L)).thenReturn(Optional.of(regiao));
        when(regiaoRepository.save(any(Regiao.class))).thenAnswer(invocation -> invocation.getArgument(0));
        RegiaoResponse result = regiaoService.atualizar(1L, dtoAtualizado);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(dtoAtualizado.getNome(), result.getNome());
        assertEquals(dtoAtualizado.getBioma(), result.getBioma());
        verify(regiaoRepository, times(1)).findById(1L);
        verify(regiaoRepository, times(1)).save(any(Regiao.class));
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar região com ID inexistente")
    void atualizar_QuandoRegiaoIdNaoExiste_DeveLancarExcecao() {
        when(regiaoRepository.findById(99L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            regiaoService.atualizar(99L, regiaoDTO);
        });
        assertEquals("Região não encontrada com id: 99", exception.getMessage());
        verify(regiaoRepository, times(1)).findById(99L);
        verify(regiaoRepository, never()).save(any(Regiao.class));
    }

    @Test
    @DisplayName("Deve deletar uma região existente com sucesso")
    void deletar_QuandoIdExiste_DeveChamarDeleteDoRepositorio() {
        when(regiaoRepository.findById(1L)).thenReturn(Optional.of(regiao));
        doNothing().when(regiaoRepository).delete(regiao);
        assertDoesNotThrow(() -> {
            regiaoService.deletar(1L);
        });
        verify(regiaoRepository, times(1)).findById(1L);
        verify(regiaoRepository, times(1)).delete(regiao);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao deletar região com ID inexistente")
    void deletar_QuandoIdNaoExiste_DeveLancarExcecao() {
        when(regiaoRepository.findById(99L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            regiaoService.deletar(99L);
        });
        assertEquals("Região não encontrada com id: 99", exception.getMessage());
        verify(regiaoRepository, times(1)).findById(99L);
        verify(regiaoRepository, never()).delete(any(Regiao.class));
    }
}


