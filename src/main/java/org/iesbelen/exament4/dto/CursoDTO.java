package org.iesbelen.exament4.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CursoDTO {

private String titulo;
private String resumen;
private BigDecimal precio;

}
