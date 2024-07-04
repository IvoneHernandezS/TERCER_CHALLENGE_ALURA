package com.aluracursos.LiterAlura.Challenge.repositorios;

import com.aluracursos.LiterAlura.Challenge.models.Idioma;
import com.aluracursos.LiterAlura.Challenge.models.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libros, Long> {
    List<Libros> findByIdiomas (Idioma idioma);

    Optional<Libros> findByTituloIgnoreCase(String titulo);

}
