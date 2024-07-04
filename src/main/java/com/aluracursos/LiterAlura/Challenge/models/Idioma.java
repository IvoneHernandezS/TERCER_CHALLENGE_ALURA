package com.aluracursos.LiterAlura.Challenge.models;

public enum Idioma {
    en ("[en]" , "Ingles"),
    es ("[es]", "Español"),
    fr ("[fr]", "Frances"),
    pt ("[pt]", "Portugues"),
    ;

    private String idiomaGutendex;
    private String idiomaEspanol;

    Idioma (String idiomaGutendex ,String idiomaEspañol) {
        this.idiomaGutendex = idiomaGutendex;
        this.idiomaEspanol = idiomaEspañol;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaGutendex.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna idioma encontrada: " + text);
    }

    public static Idioma fromEspamol (String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaEspanol.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna idioma encontrada: " + text);
    }

//    public String getIdiomaGutendex() {
//        return idiomaGutendex;
//    }
//
//    public String getIdiomaEspanol() {
//        return idiomaEspanol;
//    }
}
