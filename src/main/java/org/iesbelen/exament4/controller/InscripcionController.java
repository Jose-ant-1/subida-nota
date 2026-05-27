package org.iesbelen.exament4.controller;

import org.iesbelen.exament4.dto.InscripcionDTO;
import org.iesbelen.exament4.repository.InscripcionRepository;
import org.iesbelen.exament4.service.InscripcionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("examen/inscribir")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping("/{alumnoId}/{cursoId}")
    public InscripcionDTO inscribir(@PathVariable Long alumnoId, @PathVariable Long cursoId) {
        return inscripcionService.inscribirAlumno(alumnoId, cursoId);
    }

}
