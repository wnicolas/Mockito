package org.nibuitrago.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nibuitrago.models.Examen;
import org.nibuitrago.repositories.IExamenRepository;
import org.nibuitrago.repositories.PreguntasRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {
    @Mock
    IExamenRepository repository;
    @Mock
    PreguntasRepository preguntasRepository;

    @InjectMocks
    ExamenServiceImpl service;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);
//        repository = mock(IExamenRepository.class);
//        preguntasRepository = mock(PreguntasRepository.class);
//        service = new ExamenServiceImpl(repository, preguntasRepository);

    }

    @Test
    void findExamenPorNombre() {


        when(repository.findAll()).thenReturn(Datos.EXAMENES);
        Optional<Examen> examen = service.findExamenPorNombre("Matematicas");
        assertTrue(examen.isPresent());
        assertEquals(5L, examen.orElseThrow().getId());
        assertEquals("Matematicas", examen.get().getNombre());
    }

    @Test
    void findExamenPorNombreListaVacia() {

        List<Examen> lista = List.of();

        when(repository.findAll()).thenReturn(lista);
        Optional<Examen> examen = service.findExamenPorNombre("Matematicas");
        assertFalse(examen.isPresent());
    }

    @Test
    void testPreguntasExamenes() {
        when(repository.findAll()).thenReturn(Datos.EXAMENES);
        //when(preguntasRepository.findPreguntasByExamenId(7L)).thenReturn(Datos.PREGUNTAS);
        when(preguntasRepository.findPreguntasByExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas");
        assertNotNull(examen);
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Integrales"));
    }

    @Test
    void testPreguntasExamenesVerify() {
        when(repository.findAll()).thenReturn(Datos.EXAMENES);
        //when(preguntasRepository.findPreguntasByExamenId(7L)).thenReturn(Datos.PREGUNTAS);
        when(preguntasRepository.findPreguntasByExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas");
        assertNotNull(examen);
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Integrales"));
        verify(repository).findAll();
        //verify(preguntasRepository).findPreguntasByExamenId(5L);
        verify(preguntasRepository).findPreguntasByExamenId(anyLong());
    }

    @Test
    void testNoExisteExamenVerify() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        //when(preguntasRepository.findPreguntasByExamenId(7L)).thenReturn(Datos.PREGUNTAS);
        when(preguntasRepository.findPreguntasByExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas2");
        assertNull(examen);
        verify(repository).findAll();
        verify(preguntasRepository).findPreguntasByExamenId(5L);
    }
}