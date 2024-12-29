package org.nibuitrago.repositories;

import java.util.List;

public interface PreguntasRepository {
    List<String> findPreguntasByExamenId(Long id);
}
