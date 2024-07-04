package com.aluracursos.LiterAlura.Challenge.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;
    @OneToMany (mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libros;

    public Autor(){}

    public Autor (DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeFallecimiento = datosAutor.fechaDeFallecimiento();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        StringBuilder librosStr = new StringBuilder();
        librosStr.append("Libros: ");

        for(int i = 0; i < libros.size() ; i++) {
            librosStr.append(libros.get(i).getTitulo());
            if (i < libros.size() - 1 ){
                librosStr.append(", ");
            }
        }
        return String.format("********** Autor **********%nNombre:" +
                " %s%n%s%nFecha de Nacimiento: %s%nFecha de Deceso:" +
                " %s%n***************************%n",nombre,librosStr.toString(),fechaDeNacimiento,fechaDeFallecimiento);

    }
}
