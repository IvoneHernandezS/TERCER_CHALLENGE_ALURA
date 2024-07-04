package com.aluracursos.LiterAlura.Challenge.repositorios;

import com.aluracursos.LiterAlura.Challenge.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepositorio  extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND a.fechaDeFallecimiento >= :anio")
    List<Autor> listaAutoresVivosPorAnio (Integer anio);
}

