package org.iesbelen.exament4.repository;

import org.iesbelen.exament4.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c WHERE " +
            "(:campo = 'titulo' AND c.titulo LIKE %:valor%) OR " +
            "(:campo = 'resumen' AND c.resumen LIKE %:valor%) OR " +
            "(:valor IS NULL)")
    Page<Curso> findByFiltro(@Param("campo") String campo, @Param("valor") String valor, Pageable pageable);
}