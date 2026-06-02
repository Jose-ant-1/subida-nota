package org.iesbelen.exament4.utils;

import org.iesbelen.exament4.model.*;
import org.iesbelen.exament4.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final NivelRepository nivelRepository;
    private final InscripcionRepository inscripcionRepository;

    public DataLoader(AlumnoRepository alumnoRepository, CursoRepository cursoRepository,
                      NivelRepository nivelRepository, InscripcionRepository inscripcionRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
        this.nivelRepository = nivelRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public void run(String... args) {

        if (nivelRepository.count() > 0) {
            System.out.println("Los datos ya existen. Saltando la carga inicial.");
            return;
        }

        Nivel n1 = nivelRepository.save(Nivel.builder().nombre("Básico").descripcion("Nivel inicial").build());
        Nivel n2 = nivelRepository.save(Nivel.builder().nombre("Intermedio").descripcion("Nivel medio").build());

        for (int i = 1; i <= 5; i++) {
            alumnoRepository.save(Alumno.builder()
                    .username("alumno" + i)
                    .email("alumno" + i + "@example.com")
                    .password("pass" + i)
                    .build());
        }

        for (int i = 1; i <= 5; i++) {
            cursoRepository.save(Curso.builder()
                    .titulo("Curso " + i)
                    .resumen("Resumen del curso " + i)
                    .precio(new BigDecimal("100.00").multiply(new BigDecimal(i)))
                    .horas(10 * i)
                    .nivel(i % 2 == 0 ? n1 : n2)
                    .build());
        }

        List<Alumno> alumnos = alumnoRepository.findAll();
        List<Curso> cursos = cursoRepository.findAll();

        for (int i = 0; i < 5; i++) {
            inscripcionRepository.save(Inscripcion.builder()
                    .alumno(alumnos.get(i))
                    .curso(cursos.get(i))
                    .build());
        }
    }
}