package com.br.biosentinela.repository;

import com.br.biosentinela.model.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Page<Sensor> findByTipoIgnoreCase(String tipo, Pageable pageable);

}
