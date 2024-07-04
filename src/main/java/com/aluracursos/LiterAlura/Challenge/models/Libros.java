package com.aluracursos.LiterAlura.Challenge.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "libros")
public class Libros {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private Double numeroDeDescargas;
    @Enumerated(EnumType.STRING)
    private Idioma idiomas;
    @ManyToOne
    private Autor autor;

    public Libros(){}

    public Libros(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
        this.idiomas = Idioma.fromString(datosLibros.idiomas().toString().split(",") [0].trim());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Idioma getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idioma idiomas) {
        this.idiomas = idiomas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas +
                ", idiomas=" + idiomas +
                ", autor=" + autor +
                '}';
    }
}

