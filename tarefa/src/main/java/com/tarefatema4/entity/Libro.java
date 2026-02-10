package com.tarefatema4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Libro {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ISBN;
    private String titulo;
    private int anoPublicacion;
    private float precio;

    public Libro() {
    }

    public Libro(int ISBN, int anoPublicacion, Long id, float precio, String titulo) {
        this.ISBN = ISBN;
        this.anoPublicacion = anoPublicacion;
        this.id = id;
        this.precio = precio;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }


}
