package com.saboresdelmundo.recetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saboresdelmundo.recetas.model.Receta;
import com.saboresdelmundo.recetas.model.RecetaIngrediente;

@Repository
public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, Long> {
    List<RecetaIngrediente> findByReceta(Receta receta);

    void deleteByReceta(Receta receta);
}
