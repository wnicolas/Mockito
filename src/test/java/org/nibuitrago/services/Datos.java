package org.nibuitrago.services;

import org.nibuitrago.models.Examen;

import java.util.Arrays;
import java.util.List;

public class Datos {
    public final static List<Examen> EXAMENES = Arrays.asList(new Examen(5L, "Matematicas"),
            new Examen(6L, "Lenguaje"),
            new Examen(7L, "Historia"));

    public static final List<String> PREGUNTAS = Arrays.asList("Geometria", "Aritmetica", "Integrales", "Derivadas", "Trigonometria");

    public static final Examen EXAMEN = new Examen(8L, "Fisica");
}
