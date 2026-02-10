package com.tarefatema4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefatema4.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
