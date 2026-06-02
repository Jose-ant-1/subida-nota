package org.iesbelen.exament4.controller;

import org.iesbelen.exament4.dto.InscripcionDTO;
import org.iesbelen.exament4.service.InscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examen/inscribir")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping("/{alumnoId}/{cursoId}")
    public ResponseEntity<InscripcionDTO> inscribir(@PathVariable Long alumnoId, @PathVariable Long cursoId) {
        InscripcionDTO resultado = inscripcionService.inscribirAlumno(alumnoId, cursoId);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }
}