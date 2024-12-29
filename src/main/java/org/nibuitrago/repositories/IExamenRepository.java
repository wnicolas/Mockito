package org.nibuitrago.repositories;

import org.nibuitrago.models.Examen;

import java.util.List;

public interface IExamenRepository {
    List<Examen> findAll();

    Examen guardar(Examen examen);
}
