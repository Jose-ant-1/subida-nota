package org.iesbelen.exament4.controller;

import org.iesbelen.exament4.model.Curso;
import org.iesbelen.exament4.service.CursoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/examen/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping({"", "/"})
    public Page<Curso> all(
            @RequestParam(required = false, defaultValue = "titulo") String campo,
            @RequestParam(required = false) String busqueda,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamano,
            @RequestParam(defaultValue = "precio") String ordenacion,
            @RequestParam(defaultValue = "asc") String sentido) {

        return this.cursoService.getAllFiltered(campo, busqueda, pagina, tamano, ordenacion, sentido);
    }

    @PostMapping({"", "/"})
    public Curso newCurso(@RequestBody Curso curso) {
        return this.cursoService.save(curso);
    }

    @GetMapping("/{id}")
    public Curso one(@PathVariable("id") Long id) {
        return this.cursoService.findById(id);
    }

    @PutMapping("/{id}")
    public Curso replace(@PathVariable("id") Long id, @RequestBody Curso curso) {
        return this.cursoService.replace(id, curso);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.cursoService.delete(id);
    }
}