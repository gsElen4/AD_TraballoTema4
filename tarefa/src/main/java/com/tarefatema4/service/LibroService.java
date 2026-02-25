package com.tarefatema4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tarefatema4.entity.Autor;
import com.tarefatema4.entity.Categoria;
import com.tarefatema4.entity.Editorial;
import com.tarefatema4.entity.Libro;
import com.tarefatema4.repository.AutorRepository;
import com.tarefatema4.repository.CategoriaRepository;
import com.tarefatema4.repository.EditorialRepository;
import com.tarefatema4.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear un libro
    @Transactional
    public Libro crearLibro(String isbn, String titulo, int anoPublicacion, float precio,
                            Long editorialId, Long categoriaId) {
        Libro libro = new Libro(isbn, titulo, anoPublicacion, precio);

        if (editorialId != null) {
            Optional<Editorial> editorialOpt = editorialRepository.findById(editorialId);
            editorialOpt.ifPresent(libro::setEditorial);
        }
        if (categoriaId != null) {
            Optional<Categoria> categoriaOpt = categoriaRepository.findById(categoriaId);
            categoriaOpt.ifPresent(libro::setCategoria);
        }

        return libroRepository.save(libro);
    }

    // Agregar un autor a un libro
    @Transactional
    public Libro agregarAutor(Long libroId, Long autorId) {
        Optional<Libro> libroOpt = libroRepository.findById(libroId);
        Optional<Autor> autorOpt = autorRepository.findById(autorId);

        if (libroOpt.isPresent() && autorOpt.isPresent()) {
            Libro libro = libroOpt.get();
            libro.addAutor(autorOpt.get());
            return libroRepository.save(libro);
        }
        return null;
    }

    // Listar todos los libros
    @Transactional(readOnly = true)
    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    // Obtener un libro por id
    @Transactional(readOnly = true)
    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }

    // Eliminar un libro
    @Transactional
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    // Buscar libros a partir dun ano de publicación
    @Transactional(readOnly = true)
    public List<Libro> buscarDesdeAno(int ano) {
        return libroRepository.findByAnoPublicacionGreaterThanEqual(ano);
    }

    // Buscar por ISBN
    @Transactional(readOnly = true)
    public Optional<Libro> buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn);
    }

    // Buscar por nome de editorial 
    @Transactional(readOnly = true)
    public List<Libro> buscarPorNombreEditorial(String nombreEditorial) {
        return libroRepository.findByEditorialNombreContainingIgnoreCase(nombreEditorial);
    }

    // Buscar por nome de autor 
    @Transactional(readOnly = true)
    public List<Libro> buscarPorNombreAutor(String nombreAutor) {
        return libroRepository.findByAutoresNombreContainingIgnoreCase(nombreAutor);
    }

    // Buscar por id de categoría
    @Transactional(readOnly = true)
    public List<Libro> buscarPorCategoriaId(Long categoriaId) {
        return libroRepository.findByCategoriaId(categoriaId);
    }

    // Buscar por id de autor
    @Transactional(readOnly = true)
    public List<Libro> buscarPorAutorId(Long autorId) {
        return libroRepository.findByAutoresId(autorId);
    }
}