package com.tarefatema4.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tarefatema4.entity.Autor;
import com.tarefatema4.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // Crear un autor
    @Transactional
    public Autor crearAutor(String nombre, String apellidos, Date fechaNacimiento) {
        Autor autor = new Autor(nombre, apellidos, fechaNacimiento);
        return autorRepository.save(autor);
    }

    // Listar todos los autores
    @Transactional(readOnly = true)
    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    // Obtener un autor por id
    @Transactional(readOnly = true)
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
    @Transactional
    public void eliminarAutor(Long id) {
        autorRepository.deleteById(id);
    }

    // Contar autores
    public long contarAutores() {
        return autorRepository.count();
    }

    // Traer todos os autores cos libros cargados
    @Transactional(readOnly = true)
    public List<Autor> listarTodosConLibros() {
        return autorRepository.findAllConLibros();
    }

    // Buscar por nome 
    @Transactional(readOnly = true)
    public List<Autor> buscarPorNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Buscar autores con fecha de nacimiento entre dúas fechas
    @Transactional(readOnly = true)
    public List<Autor> buscarPorRangoFechaNacimiento(Date desde, Date hasta) {
        return autorRepository.findByFechaNacimientoBetween(desde, hasta);
    }
}