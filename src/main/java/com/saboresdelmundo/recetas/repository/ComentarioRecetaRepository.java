package com.saboresdelmundo.recetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saboresdelmundo.recetas.model.ComentarioReceta;
import com.saboresdelmundo.recetas.model.Receta;

@Repository
public interface ComentarioRecetaRepository extends JpaRepository<ComentarioReceta, Long> {
    List<ComentarioReceta> findByReceta(Receta receta);
}
