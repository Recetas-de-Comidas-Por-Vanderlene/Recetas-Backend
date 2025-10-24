package com.saboresdelmundo.recetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saboresdelmundo.recetas.model.PasoReceta;
import com.saboresdelmundo.recetas.model.Receta;

@Repository
public interface PasoRecetaRepository extends JpaRepository<PasoReceta, Long> {
    List<PasoReceta> findByRecetaOrderByOrdenAsc(Receta receta);

    void deleteByReceta(Receta receta);
}
