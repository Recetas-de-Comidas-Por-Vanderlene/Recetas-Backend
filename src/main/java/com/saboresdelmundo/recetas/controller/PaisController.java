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

import com.saboresdelmundo.recetas.model.Pais;
import com.saboresdelmundo.recetas.service.PaisService;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public ResponseEntity<List<Pais>> listarPaises() {
        List<Pais> paises = paisService.listarPaises();
        return ResponseEntity.ok(paises);
    }

    @PostMapping
    public ResponseEntity<Pais> crearPais(@RequestBody Pais pais) {
        Pais nuevo = paisService.crearPais(pais);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> consultarPais(@PathVariable Long id) {
        return paisService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}