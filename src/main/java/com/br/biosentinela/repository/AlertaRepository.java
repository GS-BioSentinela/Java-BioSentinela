package com.br.biosentinela.repository;

import com.br.biosentinela.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
