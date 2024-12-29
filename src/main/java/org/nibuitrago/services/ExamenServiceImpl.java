package org.nibuitrago.services;

import org.nibuitrago.models.Examen;
import org.nibuitrago.repositories.IExamenRepository;
import org.nibuitrago.repositories.PreguntasRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements IExamenService {
    private IExamenRepository examenRepository;
    private PreguntasRepository preguntasRepository;

    public ExamenServiceImpl(IExamenRepository examenRepository, PreguntasRepository preguntasRepository) {
        this.examenRepository = examenRepository;
        this.preguntasRepository = preguntasRepository;
    }

    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
        return this.examenRepository.findAll().stream().filter(e -> e.getNombre().contains(nombre)).findFirst();
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> examenPorNombre = findExamenPorNombre(nombre);
        Examen examen = null;
        if (examenPorNombre.isPresent()) {
            examen = examenPorNombre.get();
            List<String> preguntasByExamenId = preguntasRepository.findPreguntasByExamenId(examen.getId());
            examen.setPreguntas(preguntasByExamenId);
        }
        return examen;
    }

    @Override
    public Examen guardar(Examen examen) {
        if (!examen.getPreguntas().isEmpty()) {
            preguntasRepository.guardarVarias(examen.getPreguntas());
        }
        return examenRepository.guardar(examen);
    }
}
