package com.br.biosentinela.repository;

import com.br.biosentinela.dto.AlertaStatsDTO;
import com.br.biosentinela.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    @Query("SELECT new com.br.biosentinela.dto.AlertaStatsDTO(a.tipo, COUNT(a)) FROM Alerta a GROUP BY a.tipo")
    List<AlertaStatsDTO> contarAlertasPorTipo();
}
