package com.saboresdelmundo.recetas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saboresdelmundo.recetas.model.Pais;
import com.saboresdelmundo.recetas.repository.PaisRepository;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> listarPaises() {
        return paisRepository.findAll();
    }

    public Optional<Pais> buscarPorId(Long id) {
        return paisRepository.findById(id);
    }

    public Pais crearPais(Pais pais) {
        return paisRepository.save(pais);
    }
}
