package com.br.biosentinela.service;

import com.br.biosentinela.dto.SensorDTO;
import com.br.biosentinela.dto.SensorResponse;
import com.br.biosentinela.model.Regiao;
import com.br.biosentinela.model.Sensor;
import com.br.biosentinela.repository.RegiaoRepository;
import com.br.biosentinela.repository.SensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorServiceTest {

    private SensorRepository sensorRepository;
    private RegiaoRepository regiaoRepository;
    private SensorService sensorService;

    @BeforeEach
    void setUp() {
        sensorRepository = mock(SensorRepository.class);
        regiaoRepository = mock(RegiaoRepository.class);
        sensorService = new SensorService(sensorRepository, regiaoRepository);
    }

    @Test
    void salvarSensorComSucesso() {
        SensorDTO dto = new SensorDTO();
        dto.setTipo("Temperatura");
        dto.setLocalizacao("123,456");
        dto.setRegiaoId(1L);

        Regiao regiaoMock = new Regiao(1L, "Amazonas", "Amazônico");
        when(regiaoRepository.findById(1L)).thenReturn(Optional.of(regiaoMock));

        Sensor sensorSalvo = new Sensor(1L, "Temperatura", "123,456", regiaoMock, List.of(), null);
        when(sensorRepository.save(Mockito.any())).thenReturn(sensorSalvo);

        SensorResponse response = sensorService.salvar(dto);

        assertEquals("Temperatura", response.getTipo());
        assertEquals("123,456", response.getLocalizacao());
        assertEquals("Amazonas", response.getRegiaoNome());
    }

    @Test
    void buscarPorIdExistente_deveRetornarSensorResponse() {
        Regiao regiao = new Regiao(1L, "Cerrado", "Cerrado");
        Sensor sensor = new Sensor(1L, "Fumaça", "10.10,-10.10", regiao, List.of(), null);

        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));

        SensorResponse response = sensorService.buscarPorId(1L);

        assertEquals("Fumaça", response.getTipo());
        assertEquals("10.10,-10.10", response.getLocalizacao());
        assertEquals("Cerrado", response.getRegiaoNome());
    }
}
