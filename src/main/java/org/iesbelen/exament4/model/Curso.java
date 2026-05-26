package org.iesbelen.exament4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "curso")
@Data
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer horas;

    private BigDecimal precio;

    private String resumen;

    private String titulo;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "nivel_id")
    private Nivel nivel;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "curso")
    private List<Inscripccion> inscripcciones;

}
