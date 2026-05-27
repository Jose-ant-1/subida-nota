package org.iesbelen.exament4.dto;

import lombok.Builder;
import lombok.Data;
import org.iesbelen.exament4.model.Curso;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class InscripcionDTO {
    private String username;
    private String email;
    private List<Curso> cursos;
    private BigDecimal total_inversion;
}