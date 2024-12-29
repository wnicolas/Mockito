package org.nibuitrago.services;

import org.nibuitrago.models.Examen;

import java.util.Optional;

public interface IExamenService {
    Optional<Examen> findExamenPorNombre(String nombre);
    Examen findExamenPorNombreConPreguntas(String nombre);
}
