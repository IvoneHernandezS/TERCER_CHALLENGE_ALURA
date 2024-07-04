package com.aluracursos.LiterAlura.Challenge.principal;

import com.aluracursos.LiterAlura.Challenge.models.*;
import com.aluracursos.LiterAlura.Challenge.repositorios.AutorRepositorio;
import com.aluracursos.LiterAlura.Challenge.repositorios.LibroRepositorio;
import com.aluracursos.LiterAlura.Challenge.service.ConsumoAPI;
import com.aluracursos.LiterAlura.Challenge.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private LibroRepositorio libroRepositorio;
    private AutorRepositorio autorRepositorio;



    private List<Autor> autores;
    private List<Libros> libros;

    public Principal(LibroRepositorio libroRepositorio, AutorRepositorio autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """ 
                                        
                    Selecciona una opción
                               
                    1.- Buscar libros por titulo
                    2.- Mostrar libros registrados
                    3.- Mostrar autores registrados
                    4.- Autores vivos en un año especifico
                    5.- Buscar libros por idioma
                                        
                    0.- Salir
                     
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosEnDeterminadoAno();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:


                    System.out.println("Cerrando la aplicación");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        }
    }

    private Datos getDatosLibro() {
        System.out.println("Escriba el nombre del libro que deseas buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);
        return datos;
    }

    private void buscarLibrosPorTitulo() {
        Datos datos = getDatosLibro();
        if (datos != null && !datos.resultados().isEmpty()) {
            DatosLibros primerLibro = datos.resultados().get(0);

            Libros libros = new Libros(primerLibro);
            System.out.println("***** Libro *****");
            System.out.println(libros);
            System.out.println("*****************");

            Optional<Libros> libroExiste = libroRepositorio.findByTituloIgnoreCase(libros.getTitulo());
            if (libroExiste.isPresent()) {
                System.out.println("\nEl libro ya esta registrado\n");
            } else {

                if (!primerLibro.autor().isEmpty()) {
                    DatosAutor autor = primerLibro.autor().get(0);
                    Autor autor1 = new Autor(autor);
                    Optional<Autor> autorOptional = autorRepositorio.findByNombreIgnoreCase(autor1.getNombre());

                    if (autorOptional.isPresent()) {
                        Autor autorExiste = autorOptional.get();
                        libros.setAutor(autorExiste);
                        libroRepositorio.save(libros);
                    } else {
                        Autor autorNuevo = autorRepositorio.save(autor1);
                        libros.setAutor(autorNuevo);
                        libroRepositorio.save(libros);
                    }

                    Double numeroDescargas = libros.getNumeroDeDescargas() != null ? libros.getNumeroDeDescargas() : 0;
                    System.out.println("********** Libro **********");
                    System.out.printf("Titulo: %s%nAutor: %s%nIdioma: %s%nNumero de Descargas: %s%n",
                            libros.getTitulo(), autor1.getNombre(), libros.getIdiomas(), libros.getNumeroDeDescargas());
                    System.out.println("***************************\n");
                } else {
                    System.out.println("Sin autor");
                }
            }
        } else {
            System.out.println("libro no encontrado");
        }

    }

    private void mostrarLibrosRegistrados() {
        libros = libroRepositorio.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void mostrarAutoresRegistrados() {
        autores = autorRepositorio.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    private void mostrarAutoresVivosEnDeterminadoAno() {
        System.out.println("Ingresa el año vivo de autor(es) que desea buscar: ");
        var anio = teclado.nextInt();
        autores = autorRepositorio.listaAutoresVivosPorAnio(anio);
        autores.stream()
                .forEach(System.out::println);
    }

    private List<Libros> datosBusquedaLenguaje(String idioma) {
        var dato = Idioma.fromString(idioma);
        System.out.println("Lenguaje buscado: " + dato);

        List<Libros> libroPorIdioma = libroRepositorio.findByIdiomas(dato);
        return libroPorIdioma;
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("Selecciona el lenguaje/idioma que deseas buscar: ");

        var opcion = -1;
        while (opcion != 0) {
            var opciones = """
                    1. en - Ingles
                    2. es - Español
                    3. fr - Francés
                    4. pt - Portugués
                                        
                    0. Volver a Las opciones anteriores
                    """;
            System.out.println(opciones);
            while (!teclado.hasNextInt()) {
                System.out.println("Formato inválido, ingrese un número que esté disponible en el menú");
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    List<Libros> librosEnIngles = datosBusquedaLenguaje("[en]");
                    librosEnIngles.forEach(System.out::println);
                    break;
                case 2:
                    List<Libros> librosEnEspanol = datosBusquedaLenguaje("[es]");
                    librosEnEspanol.forEach(System.out::println);
                    break;
                case 3:
                    List<Libros> librosEnFrances = datosBusquedaLenguaje("[fr]");
                    librosEnFrances.forEach(System.out::println);
                    break;
                case 4:
                    List<Libros> librosEnPortugues = datosBusquedaLenguaje("[pt]");
                    librosEnPortugues.forEach(System.out::println);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Ningún idioma seleccionado");
            }
        }
    }
}
