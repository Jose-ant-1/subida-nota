package org.iesbelen.exament4.service;

import org.iesbelen.exament4.model.Curso;
import org.iesbelen.exament4.repository.CursoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> getAll() {
        return this.cursoRepository.findAll();
    }

    public Curso save(Curso curso) {
        return this.cursoRepository.save(curso);
    }

    public Curso findById(Long id) {
        return this.cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe curso con id: " + id));
    }

    public Curso replace(Long id, Curso curso) {
        return this.cursoRepository.findById(id).map(c -> {
            c.setTitulo(curso.getTitulo());
            c.setResumen(curso.getResumen());
            c.setPrecio(curso.getPrecio());
            c.setHoras(curso.getHoras());
            c.setNivel(curso.getNivel());
            return this.cursoRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("No existe curso con id: " + id));
    }

    public void delete(Long id) {
        this.cursoRepository.findById(id).map(c -> {
            this.cursoRepository.delete(c);
            return c;
        }).orElseThrow(() -> new RuntimeException("No existe curso con id: " + id));
    }

    public Page<Curso> getAllFiltered(String campo, String busqueda, int pagina, int tamano, String ordenacion, String sentido) {
         // ordenar
        Sort sort = Sort.by(sentido.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                (ordenacion == null || ordenacion.isEmpty()) ? "precio" : ordenacion);

        // paginar
        Pageable pageable = PageRequest.of(pagina, tamano, sort);

        return cursoRepository.findByFiltro(campo, busqueda, pageable);
    }

}