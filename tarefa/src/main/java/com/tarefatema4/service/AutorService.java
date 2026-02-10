package com.tarefatema4.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefatema4.entity.Autor;
import com.tarefatema4.repository.AutorRepository;

import jakarta.transaction.Transactional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // Crear un autor
    public Autor crearAutor(String nombre, String apellidos, Date fechaNacimiento) {
        Autor autor = new Autor(nombre, apellidos, fechaNacimiento);
        return autorRepository.save(autor);
    }

    // Listar todos los autores
    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    // Obtener un autor por id
   // @Transactional(readOnly = true)
    public Optional<Autor> obtenerPorId(Long id) {
        return autorRepository.findById(id);
    }

    // Actualizar un autor
    @Transactional
    public Autor actualizarAutor(Long id, String nombre, String apellidos, Date fechaNacimiento) {
        Optional<Autor> autorOpt = autorRepository.findById(id);
        if (autorOpt.isPresent()) {
            Autor autor = autorOpt.get();
            autor.setNombre(nombre);
            autor.setApellidos(apellidos);
            autor.setFechaNacimiento(fechaNacimiento);
            return autorRepository.save(autor);
        }
        return null;
    }

    // Eliminar un autor
    public void eliminarAutor(Long id) {
        autorRepository.deleteById(id);
    }

    // Contar autores
    public long contarAutores() {
        return autorRepository.count();
    }

    // Guardar autor (guardar entidad completa, útil tras asignar curso)
    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

  
}
