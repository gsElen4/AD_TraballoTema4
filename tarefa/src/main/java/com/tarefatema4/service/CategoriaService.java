package com.tarefatema4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tarefatema4.entity.Categoria;
import com.tarefatema4.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear una categoría
    @Transactional
    public Categoria crearCategoria(String nombre, String descripcion) {
        Categoria categoria = new Categoria(nombre, descripcion);
        return categoriaRepository.save(categoria);
    }

    // Listar todas las categorías
    @Transactional(readOnly = true)
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    // Obtener una categoría por id
    @Transactional(readOnly = true)
    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    // Eliminar categoría 
    @Transactional
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}