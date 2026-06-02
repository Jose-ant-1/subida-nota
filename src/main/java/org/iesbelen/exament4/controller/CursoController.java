package org.iesbelen.exament4.controller;

import org.iesbelen.exament4.dto.CursoDTO;
import org.iesbelen.exament4.model.Curso;
import org.iesbelen.exament4.service.CursoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examen/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Page<CursoDTO>> all(
            @RequestParam(required = false, defaultValue = "titulo") String campo,
            @RequestParam(required = false) String busqueda,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamano,
            @RequestParam(defaultValue = "precio") String ordenacion,
            @RequestParam(defaultValue = "asc") String sentido) {

        return ResponseEntity.ok(this.cursoService.getAllFiltered(campo, busqueda, pagina, tamano, ordenacion, sentido));
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Curso> newCurso(@RequestBody Curso curso) {
        return new ResponseEntity<>(this.cursoService.save(curso), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> one(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.cursoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> replace(@PathVariable("id") Long id, @RequestBody Curso curso) {
        return ResponseEntity.ok(this.cursoService.replace(id, curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}