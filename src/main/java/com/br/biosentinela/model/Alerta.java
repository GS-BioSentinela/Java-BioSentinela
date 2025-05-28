package com.br.biosentinela.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
