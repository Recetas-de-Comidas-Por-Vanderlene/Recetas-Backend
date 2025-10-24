package com.saboresdelmundo.recetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saboresdelmundo.recetas.model.ComentarioReceta;
import com.saboresdelmundo.recetas.service.ComentarioService;

@RestController
@RequestMapping("/api/recetas/{id}/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<ComentarioReceta> agregarComentario(@PathVariable Long id,
            @RequestBody ComentarioReceta comentario) {
        // Agregar comentario a receta
        ComentarioReceta nuevo = comentarioService.agregarComentario(id, comentario);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<ComentarioReceta>> verComentarios(@PathVariable Long id) {
        List<ComentarioReceta> comentarios = comentarioService.verComentarios(id);
        return ResponseEntity.ok(comentarios);
    }
}
