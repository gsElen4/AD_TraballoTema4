package com.tarefatema4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Editorial {
    @Id
    private Long id;
    private String nombre;
    private String pais;

    public Editorial() {
    }

    public Editorial(Long id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
