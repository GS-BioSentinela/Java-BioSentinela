package com.br.biosentinela.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tipo de alerta é obrigatório")
    private String tipo;

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    @JsonIgnoreProperties("alertas")
    private Sensor sensor;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

}
