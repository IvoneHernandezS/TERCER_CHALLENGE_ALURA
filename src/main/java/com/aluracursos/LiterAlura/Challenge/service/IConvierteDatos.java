package com.aluracursos.LiterAlura.Challenge.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase );
}
