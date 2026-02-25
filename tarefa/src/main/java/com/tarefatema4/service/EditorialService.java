package com.tarefatema4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tarefatema4.entity.Editorial;
import com.tarefatema4.repository.EditorialRepository;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    // Crear una editorial
    @Transactional
    public Editorial crearEditorial(String nombre, String pais) {
        Editorial editorial = new Editorial(nombre, pais);
        return editorialRepository.save(editorial);
    }

    // Listar todas las editoriales
    @Transactional(readOnly = true)
    public List<Editorial> listarTodas() {
        return editorialRepository.findAll();
    }

    // Obtener una editorial por id
    @Transactional(readOnly = true)
    public Optional<Editorial> obtenerPorId(Long id) {
        return editorialRepository.findById(id);
    }

    // Eliminar una editorial
    @Transactional
    public void eliminarEditorial(Long id) {
        editorialRepository.deleteById(id);
    }
}