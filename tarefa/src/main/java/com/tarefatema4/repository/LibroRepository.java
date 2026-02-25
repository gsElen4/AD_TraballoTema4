package com.tarefatema4.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefatema4.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByAnoPublicacionGreaterThanEqual(int ano);

    Optional<Libro> findByIsbn(String isbn);

    List<Libro> findByEditorialNombreContainingIgnoreCase(String nombreEditorial);

    List<Libro> findByAutoresNombreContainingIgnoreCase(String nombreAutor);

    List<Libro> findByCategoriaId(Long categoriaId);

    List<Libro> findByAutoresId(Long autorId);
}