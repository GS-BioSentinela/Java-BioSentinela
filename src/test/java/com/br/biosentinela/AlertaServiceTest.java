
package com.br.biosentinela;

import com.br.biosentinela.dto.AlertaDTO;
import com.br.biosentinela.dto.AlertaResponse;
import com.br.biosentinela.dto.AlertaStatsDTO;
import com.br.biosentinela.exception.ResourceNotFoundException;
import com.br.biosentinela.model.Alerta;
import com.br.biosentinela.model.Regiao;
import com.br.biosentinela.model.Sensor;
import com.br.biosentinela.repository.AlertaRepository;
import com.br.biosentinela.repository.SensorRepository;
import com.br.biosentinela.service.AlertaService;
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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertaServiceTest {

    @Mock
    private AlertaRepository alertaRepository;

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private AlertaService alertaService;

    private Alerta alerta;
    private Sensor sensor;
    private Regiao regiao;
    private AlertaDTO alertaDTO;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        regiao = new Regiao(1L, "Pantanal", "Pantanal");
        sensor = new Sensor(1L, "Temperatura", "-19.00, -57.00", regiao, null, LocalDateTime.now());
        alerta = new Alerta(1L, "Temperatura Alta", "Sensor X reportou 40C", sensor, LocalDateTime.now());
        alertaDTO = new AlertaDTO();
        alertaDTO.setTipo("Temperatura Alta");
        alertaDTO.setMensagem("Sensor X reportou 40C");
        alertaDTO.setSensorId(1L);

        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("Deve listar alertas paginados com sucesso")
    void listarPaginado_DeveRetornarPaginaDeAlertas() {
        // Arrange
        Page<Alerta> page = new PageImpl<>(Collections.singletonList(alerta), pageable, 1);
        when(alertaRepository.findAll(pageable)).thenReturn(page);
        Page<AlertaResponse> result = alertaService.listarPaginado(pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals(alerta.getTipo(), result.getContent().get(0).getTipo());
        verify(alertaRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Deve buscar alerta por ID com sucesso")
    void buscarPorId_QuandoIdExiste_DeveRetornarAlerta() {

        when(alertaRepository.findById(1L)).thenReturn(Optional.of(alerta));


        AlertaResponse result = alertaService.buscarPorId(1L);


        assertNotNull(result);
        assertEquals(alerta.getId(), result.getId());
        assertEquals(alerta.getTipo(), result.getTipo());
        verify(alertaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao buscar alerta por ID inexistente")
    void buscarPorId_QuandoIdNaoExiste_DeveLancarExcecao() {

        when(alertaRepository.findById(99L)).thenReturn(Optional.empty());


        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            alertaService.buscarPorId(99L);
        });
        assertEquals("Alerta não encontrado com id: 99", exception.getMessage());
        verify(alertaRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Deve salvar um novo alerta com sucesso")
    void salvar_QuandoDadosValidos_DeveRetornarAlertaSalvo() {
        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));
        when(alertaRepository.save(any(Alerta.class))).thenAnswer(invocation -> {
            Alerta a = invocation.getArgument(0);
            a.setId(2L);
            return a;
        });

        AlertaResponse result = alertaService.salvar(alertaDTO);
        assertNotNull(result);
        assertEquals(alertaDTO.getTipo(), result.getTipo());
        assertEquals(alertaDTO.getMensagem(), result.getMensagem());
        assertEquals(sensor.getLocalizacao(), result.getSensorLocalizacao());
        assertNotNull(result.getId()); // Verifica se um ID foi atribuído
        verify(sensorRepository, times(1)).findById(1L);
        verify(alertaRepository, times(1)).save(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao salvar alerta com Sensor ID inexistente")
    void salvar_QuandoSensorIdNaoExiste_DeveLancarExcecao() {
        when(sensorRepository.findById(99L)).thenReturn(Optional.empty());
        alertaDTO.setSensorId(99L);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            alertaService.salvar(alertaDTO);
        });
        assertEquals("Sensor não encontrado", exception.getMessage());
        verify(sensorRepository, times(1)).findById(99L);
        verify(alertaRepository, never()).save(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve atualizar um alerta existente com sucesso")
    void atualizar_QuandoDadosValidosEIdExiste_DeveRetornarAlertaAtualizado() {
        AlertaDTO dtoAtualizado = new AlertaDTO();
        dtoAtualizado.setTipo("Fumaça Detectada");
        dtoAtualizado.setMensagem("Nível alto de fumaça");
        dtoAtualizado.setSensorId(1L);

        when(alertaRepository.findById(1L)).thenReturn(Optional.of(alerta));
        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));
        when(alertaRepository.save(any(Alerta.class))).thenAnswer(invocation -> invocation.getArgument(0));
        AlertaResponse result = alertaService.atualizar(1L, dtoAtualizado);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(dtoAtualizado.getTipo(), result.getTipo());
        assertEquals(dtoAtualizado.getMensagem(), result.getMensagem());
        verify(alertaRepository, times(1)).findById(1L);
        verify(sensorRepository, times(1)).findById(1L);
        verify(alertaRepository, times(1)).save(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar alerta com ID inexistente")
    void atualizar_QuandoAlertaIdNaoExiste_DeveLancarExcecao() {
        when(alertaRepository.findById(99L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            alertaService.atualizar(99L, alertaDTO);
        });
        assertEquals("Alerta não encontrado com id: 99", exception.getMessage());
        verify(alertaRepository, times(1)).findById(99L);
        verify(sensorRepository, never()).findById(anyLong());
        verify(alertaRepository, never()).save(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar alerta com Sensor ID inexistente")
    void atualizar_QuandoSensorIdNaoExiste_DeveLancarExcecao() {
        alertaDTO.setSensorId(99L);
        when(alertaRepository.findById(1L)).thenReturn(Optional.of(alerta));
        when(sensorRepository.findById(99L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            alertaService.atualizar(1L, alertaDTO);
        });
        assertEquals("Sensor não encontrado", exception.getMessage());
        verify(alertaRepository, times(1)).findById(1L);
        verify(sensorRepository, times(1)).findById(99L);
        verify(alertaRepository, never()).save(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve deletar um alerta existente com sucesso")
    void deletar_QuandoIdExiste_DeveChamarDeleteDoRepositorio() {
        when(alertaRepository.findById(1L)).thenReturn(Optional.of(alerta));
        doNothing().when(alertaRepository).delete(alerta);
        assertDoesNotThrow(() -> {
            alertaService.deletar(1L);
        });
        verify(alertaRepository, times(1)).findById(1L);
        verify(alertaRepository, times(1)).delete(alerta);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao deletar alerta com ID inexistente")
    void deletar_QuandoIdNaoExiste_DeveLancarExcecao() {
        when(alertaRepository.findById(99L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            alertaService.deletar(99L);
        });
        assertEquals("Alerta não encontrado com id: 99", exception.getMessage());
        verify(alertaRepository, times(1)).findById(99L);
        verify(alertaRepository, never()).delete(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve obter estatísticas de alertas por tipo com sucesso")
    void obterEstatisticasPorTipo_DeveRetornarListaDeStats() {
        List<AlertaStatsDTO> stats = Collections.singletonList(new AlertaStatsDTO("Temperatura Alta", 5L));
        when(alertaRepository.contarAlertasPorTipo()).thenReturn(stats);
        List<AlertaStatsDTO> result = alertaService.obterEstatisticasPorTipo();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Temperatura Alta", result.get(0).getTipo());
        assertEquals(5L, result.get(0).getQuantidade());
        verify(alertaRepository, times(1)).contarAlertasPorTipo();
    }
}


