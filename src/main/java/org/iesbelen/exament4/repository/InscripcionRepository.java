package org.iesbelen.exament4.repository;

import org.iesbelen.exament4.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByAlumnoId(Long alumnoId);
}