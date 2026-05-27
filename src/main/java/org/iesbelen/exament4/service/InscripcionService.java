package org.iesbelen.exament4.service;

import org.iesbelen.exament4.dto.InscripcionDTO;
import org.iesbelen.exament4.model.Alumno;
import org.iesbelen.exament4.model.Curso;
import org.iesbelen.exament4.model.Inscripccion;
import org.iesbelen.exament4.repository.AlumnoRepository;
import org.iesbelen.exament4.repository.CursoRepository;
import org.iesbelen.exament4.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InscripcionService {

    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(AlumnoRepository alumnoRepository, CursoRepository cursoRepository, InscripcionRepository inscripcionRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    public InscripcionDTO inscribirAlumno(Long alumnoId, Long cursoId) {

        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Guardar la nueva inscripción
        Inscripccion inscripcion = Inscripccion.builder()
                .alumno(alumno)
                .curso(curso)
                .build();
        inscripcionRepository.save(inscripcion);

        // Obtener todas las inscripciones del alumno para calcular la inversión
        List<Inscripccion> inscripciones = inscripcionRepository.findByAlumnoId(alumnoId);
        List<Curso> cursosInscritos = inscripciones.stream().map(Inscripccion::getCurso).toList();

        BigDecimal total = cursosInscritos.stream()
                .map(Curso::getPrecio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return InscripcionDTO.builder()
                .username(alumno.getUsername())
                .email(alumno.getEmail())
                .cursos(cursosInscritos)
                .total_inversion(total)
                .build();
    }



}
