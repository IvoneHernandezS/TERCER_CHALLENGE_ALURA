package com.aluracursos.LiterAlura.Challenge;

import com.aluracursos.LiterAlura.Challenge.principal.Principal;
import com.aluracursos.LiterAlura.Challenge.repositorios.AutorRepositorio;
import com.aluracursos.LiterAlura.Challenge.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraChallengeApplication implements CommandLineRunner {

	@Autowired
	private AutorRepositorio autorRepositorio;
	@Autowired
	private LibroRepositorio libroRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepositorio, autorRepositorio);
		principal.muestraElMenu();

	}

}
