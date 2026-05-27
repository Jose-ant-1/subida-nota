package org.iesbelen.exament4.repository;

import org.iesbelen.exament4.model.Inscripccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripccion, Long> {
    List<Inscripccion> findByAlumnoId(Long alumnoId);
}