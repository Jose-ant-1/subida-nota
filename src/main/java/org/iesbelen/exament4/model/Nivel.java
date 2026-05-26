package org.iesbelen.exament4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "nivel")
@Data
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Nivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "nivel")
    @ToString.Exclude
    private List<Curso> cursos;


}
