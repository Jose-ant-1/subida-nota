package org.iesbelen.exament4.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inscripcion")
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "curso_id")
    private Curso curso;
}