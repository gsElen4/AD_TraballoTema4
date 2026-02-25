package com.tarefatema4.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tarefatema4.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllConLibros();

    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    List<Autor> findByFechaNacimientoBetween(Date desde, Date hasta);
}