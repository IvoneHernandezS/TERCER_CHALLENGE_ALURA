package com.aluracursos.LiterAlura.Challenge.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias("count") Integer cuenta,
        @JsonAlias("results") List<DatosLibros> resultados
) {
}
