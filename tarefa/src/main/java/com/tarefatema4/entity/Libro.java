package com.tarefatema4.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String titulo;
    private int anoPublicacion;
    private float precio;

    // Libro SEMPRE ten os autores cargados (EAGER). Libro é o dono da relación.
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "libro_autor",
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    // Libro -> Editorial (ManyToOne)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    // Libro -> Categoria (ManyToOne)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Libro() {
    }

    public Libro(String isbn, String titulo, int anoPublicacion, float precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anoPublicacion = anoPublicacion;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Helper para agregar autor manteniendo sincronización bidireccional
    public void addAutor(Autor autor) {
        if (!autores.contains(autor)) {
            autores.add(autor);
            autor.getLibros().add(this);
        }
    }

    // Helper para eliminar autor
    public void removeAutor(Autor autor) {
        if (autores.remove(autor)) {
            autor.getLibros().remove(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Libro{id=").append(id)
          .append(", isbn='").append(isbn).append("'")
          .append(", titulo='").append(titulo).append("'")
          .append(", ano=").append(anoPublicacion)
          .append(", precio=").append(precio)
          .append(", editorial=").append(editorial != null ? editorial.getNombre() : "null")
          .append(", categoria=").append(categoria != null ? categoria.getNombre() : "null")
          .append(", autores=[");
        autores.forEach(a -> sb.append(a.getNombre()).append(" ").append(a.getApellidos()).append("; "));
        sb.append("]}");
        return sb.toString();
    }
}