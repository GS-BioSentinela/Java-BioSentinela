package com.br.biosentinela.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    @NotBlank(message = "Localização é obrigatória")
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "regiao_id")
    private Regiao regiao;

    @OneToMany(mappedBy = "sensor")
    @JsonIgnore
    private List<Alerta> alertas;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dataCriacao;
}
